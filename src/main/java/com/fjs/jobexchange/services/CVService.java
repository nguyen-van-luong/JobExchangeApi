package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.ApiException;
import com.fjs.jobexchange.dtos.CVDto;
import com.fjs.jobexchange.dtos.ResultCount;
import com.fjs.jobexchange.models.*;
import com.fjs.jobexchange.repositories.CVRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CVService {
    private final CVRepository cvRepository;
    private final StudentService studentService;
    private final UserService userService;

    public CV get(Integer id) {
        CV cv = cvRepository.findById(id)
                .orElseThrow(() -> new ApiException("Hồ sơ tồn tại", HttpStatus.NOT_FOUND));

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUsername(name);
        int userId = cv.getStudent().getId();

        if (cv.getIsPrivate() && user != null && user.getUserId() != userId) {
            throw new ApiException("Bài viết tạm thời không có sẵn với mọi người", HttpStatus.FORBIDDEN);
        }

        return cv;
    }

    @Transactional
    public CV create(CVDto cvDto, List<Skill> skills, List<IndustrySpecialization> industrySpecializations) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CV cv = CV
                .builder()
                .student(studentService.findById(user.getUserId()))
                .fullname(cvDto.getFullname())
                .birthday(cvDto.getBirthday())
                .email(cvDto.getEmail())
                .province(cvDto.getProvince())
                .address(cvDto.getAddress())
                .sex(cvDto.getSex())
                .phoneNumber(cvDto.getPhoneNumber())
                .positionCurrent(cvDto.getPositionCurrent())
                .positionWant(cvDto.getPositionWant())
                .degree(cvDto.getDegree())
                .experience(cvDto.getExperience())
                .salaryWant(cvDto.getSalaryWant())
                .workingForm(cvDto.getWorkingForm())
                .skills( new HashSet<>(skills))
                .industrySpecializations(new HashSet<>(industrySpecializations))
                .description(cvDto.getDescription())
                .workExperience(cvDto.getWorkExperience())
                .isPrivate(cvDto.isPrivate())
                .build();
        try {
            cv = cvRepository.save(cv);
            return cv;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException("Có lỗi xảy ra khi tạo Cv. Vui lòng thử lại!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResultCount<CV> search(String searchContent, String industry, String province, Integer page, Integer limit) {
        Page<CV> cvPage;
        Pageable pageable = (page == null || limit == null || page < 1 || limit < 1)
                ? Pageable.unpaged()
                : PageRequest.of(page - 1, limit, Sort.Direction.DESC,"updatedAt");

        cvPage = cvRepository.findByProvinceAndIndustryAndMore(searchContent,industry,province,pageable);
        try {
            List<CV> cvs = cvPage.toList();
            long count = cvPage.getTotalElements();

            return new ResultCount<>(cvs, count);
        } catch (Exception e) {
            throw new ApiException("Lỗi! không thể tim kiếm", HttpStatus.FORBIDDEN);
        }
    }

    public CV findMyCV(Integer id){
        CV cv =  cvRepository.findById(id)
                .orElseThrow(() -> new ApiException("CV không tồn tại!", HttpStatus.BAD_REQUEST));
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUsername(name);
        int userId = cv.getStudent().getId();

        if (user != null && user.getUserId() != userId) {
            throw new ApiException("Bạn không có quyền sử dụng CV này", HttpStatus.FORBIDDEN);
        } else {
            return cv;
        }
    }

    public List<CV> findByStudentId(Integer studentId){
        return cvRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ApiException("CV không tồn tại!", HttpStatus.BAD_REQUEST));
    }

}
