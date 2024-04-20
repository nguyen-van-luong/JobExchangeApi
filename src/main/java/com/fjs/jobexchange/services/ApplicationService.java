package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.ApiException;
import com.fjs.jobexchange.dtos.ApplicationDto;
import com.fjs.jobexchange.dtos.ResultCount;
import com.fjs.jobexchange.models.*;
import com.fjs.jobexchange.repositories.ApplicationRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final CVService cvService;
    private final  JobService jobService;
    private  final StudentService studentService;

    public Application createApplication(Integer cvId, Integer jobId) {
        CV cv=cvService.findMyCV(cvId);
        Job job=jobService.get(jobId);
        ApplicationId applicationId = new ApplicationId();
        applicationId.setCvId(cvId);
        applicationId.setJobId(jobId);
        return Application.builder()
                .id(applicationId)
                .cv(cv)
                .job(job)
                .status("Chưa xem").build();
    }

    @Transactional
    public void submitCVs(List<Integer> cvIds, Integer jobJd) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        delete(user.getUserId(), jobJd);
        List<Application> applications = new ArrayList<>();
        for(Integer cvId : cvIds) {
            applications.add(createApplication(cvId, jobJd));
        }

        try {
            applicationRepository.saveAll(applications);
        } catch (Exception e) {
            throw new ApiException("Có lỗi xảy ra khi nộp hồ sơ! Vui lòng thử lại!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Application findByCvIdAndJobId(Integer cvId, Integer jobId) {
        return applicationRepository.findByCvIdAndJobId(cvId, jobId)
                .orElseThrow(() -> new ApiException("Bài đăng hoặc hô sơ không tồn tại", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public void delete(Integer studentId, Integer jobId) {
        applicationRepository.deleteAll(findByJobIdAndStudentId(studentId, jobId));
    }

    public List<Application> findByJobIdAndStudentId(Integer studentId, Integer jobId) {
        Student student = studentService.findById(studentId);
        Job job = jobService.get(jobId);
        return applicationRepository.findByJobIdAndStudentId(job.getId(), student.getId());
    }

    @Transactional
    public ResultCount<Application> search(String title, String status, Integer page, Integer limit) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Application> applicationPage;
        Pageable pageable = (page == null || limit == null || page < 1 || limit < 1)
                ? Pageable.unpaged()
                : PageRequest.of(page - 1, limit, Sort.Direction.DESC,"createdAt");

        applicationPage = applicationRepository.findByJobEmployerIdAndJobTitleAndStatus(user.getUserId(),title,status,pageable);
        try {
            List<Application> applications = applicationPage.toList();
            long count = applicationPage.getTotalElements();

            return new ResultCount<>(applications, count);
        } catch (Exception e) {
            throw new ApiException("Lỗi! không thể tim kiếm", HttpStatus.FORBIDDEN);
        }
    }

    @Transactional
    public void update(ApplicationDto applicationNew) {
        Application application = applicationRepository.findByCvIdAndJobId(applicationNew.getCvId(), applicationNew.getJobId())
                .orElseThrow(() -> new ApiException("Hồ sơ ứng tuyển không tồn tại", HttpStatus.NOT_FOUND));
        application.setStatus(applicationNew.getStatus());

        applicationRepository.save(application);
    }

    @Transactional
    public void deleteByApplication(List<ApplicationDto> applicationDtos) {
        List<Application> applications = new ArrayList<>();
        for(ApplicationDto applicationDto : applicationDtos)
            applications.add(findByCvIdAndJobId(applicationDto.getCvId(), applicationDto.getJobId()));
        applicationRepository.deleteAll(applications);
    }
}
