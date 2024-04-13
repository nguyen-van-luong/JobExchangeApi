package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.*;
import com.fjs.jobexchange.models.*;
import com.fjs.jobexchange.repositories.AuthenticationRepository;
import com.fjs.jobexchange.repositories.EmployerRepository;
import com.fjs.jobexchange.repositories.StudentRepository;
import com.fjs.jobexchange.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final EmployerRepository employerRepository;
    private final StudentRepository studentRepository;
    private final AuthenticationRepository authenticationRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public User signUpEmployer(SignUpEmployerRequest request) {

        if (checkUser(request.getUsername())) {
            throw new ApiException("Username đã tồn tại", HttpStatus.BAD_REQUEST);
        }

        Employer employer = Employer.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();

        employer = employerRepository.save(employer);

        User user = User.builder()
                .userId(employer.getId())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_employer)
                .build();
        return userRepository.save(user);
    }
    public User signUpStudent(SignUpStudentRequest request) {

        if (checkUser(request.getUsername())) {
            throw new ApiException("Username đã tồn tại", HttpStatus.BAD_REQUEST);
        }

        Student student = Student.builder()
                .email(request.getEmail())
                .fullname(request.getFullname())
                .build();

        student = studentRepository.save(student);

        User user = User.builder()
                .userId(student.getId())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_student)
                .build();
        return userRepository.save(user);
    }
    public JsonWebToken signIn(SignInRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ApiException("Tài khoản không hợp lệ.", HttpStatus.UNAUTHORIZED));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            throw new ApiException("Mật khẩu không hợp lệ.", HttpStatus.UNAUTHORIZED);
        }


        var authentication = authenticationRepository.findByUser(user);
        if (authentication.isPresent()) {
            var refreshToken = authentication.get().getRefreshToken();
            if (!jwtService.isTokenExpired(refreshToken, true)) {
                return JsonWebToken.builder().token(refreshToken).build();
            }
        }

        var refreshToken = jwtService.generateToken(user, true);
        var newAuthentication = Authentication.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        updateOrCreateIfNot(newAuthentication);
        return JsonWebToken.builder().token(refreshToken).build();
    }
    public void updateOrCreateIfNot(Authentication newAuthentication) {
        Optional<Authentication> authentication = authenticationRepository.findByUser(newAuthentication.getUser());
        if (authentication.isEmpty()) {
            authenticationRepository.save(newAuthentication);
        } else {
            var existingAuthentication = authentication.get();
            existingAuthentication.setRefreshToken(newAuthentication.getRefreshToken());
            authenticationRepository.save(existingAuthentication);
        }
    }
    boolean checkUser(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
    public JsonWebToken refreshToken(String refreshToken) {
        RecordAuthUser recordAuthUser = getAuthenticationAndUser(refreshToken);
        var user = recordAuthUser.user();
        var authentication = recordAuthUser.authentication();

        if(jwtService.isTokenExpired(authentication.getRefreshToken(), true))
            throw new ApiException("Phiên truy cập đã hết hạn. Vui lòng đăng nhập lại!", HttpStatus.PRECONDITION_FAILED);

        var claims = new HashMap<String, Object>();
        claims.put("role", user.getRole());
        claims.put("userId", user.getUserId());

        var accessToken = jwtService.generateToken(claims, user, false);
        return JsonWebToken.builder().token(accessToken).build();
    }
    private RecordAuthUser getAuthenticationAndUser(String refreshToken) {
        String username = jwtService.extractUserName(refreshToken, true);
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException("Có lỗi xảy ra. Vui lòng đăng nhập lại!", HttpStatus.NOT_ACCEPTABLE));
        var authentication = authenticationRepository.findByUser(user)
                .orElseThrow(() -> new ApiException("Có lỗi xảy ra. Vui lòng đăng nhập lại!", HttpStatus.NOT_ACCEPTABLE));
        if (!authentication.getRefreshToken().equals(refreshToken))
            throw new ApiException("Có lỗi xảy ra. Vui lòng đăng nhập lại!", HttpStatus.NOT_ACCEPTABLE);

        return RecordAuthUser.builder().user(user).authentication(authentication).build();
    }
    public void logout(String refreshToken) {
        var user = getAuthenticationAndUser(refreshToken).user();
        authenticationRepository.deleteByUser(user);
    }
}
