package com.fjs.jobexchange.controllers;

import com.fjs.jobexchange.dtos.JsonWebToken;
import com.fjs.jobexchange.dtos.SignInRequest;
import com.fjs.jobexchange.dtos.SignUpEmployerRequest;
import com.fjs.jobexchange.dtos.SignUpStudentRequest;
import com.fjs.jobexchange.models.User;
import com.fjs.jobexchange.services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup/employer")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpEmployerRequest signup) {
        User newUser = authenticationService.signUpEmployer(signup);
        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Đăng ký không thành công", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/signup/student")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpStudentRequest signup) {

        User newUser = authenticationService.signUpStudent(signup);
        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Đăng ký không thành công", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody SignInRequest signin) {
        return new ResponseEntity<>(authenticationService.signIn(signin), HttpStatus.OK);
    }
    @GetMapping("/refresh-token")
    public ResponseEntity<?> getRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chưa cung cấp refresh token");
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh_token")) {
                String refreshToken = cookie.getValue();
                JsonWebToken response = authenticationService.refreshToken(refreshToken);
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chưa cung cấp refresh token");
    }
}
