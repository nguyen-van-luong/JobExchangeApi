package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.ApiException;
import com.fjs.jobexchange.dtos.JobDto;
import com.fjs.jobexchange.dtos.ProvinceDto;
import com.fjs.jobexchange.dtos.ResultCount;
import com.fjs.jobexchange.models.*;
import com.fjs.jobexchange.repositories.JobRepository;
import com.fjs.jobexchange.repositories.ProvinceRepository;
import com.fjs.jobexchange.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final EmployerService employerService;
    private final UserService userService;

    public Job get(Integer id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ApiException("Bài viết không tồn tại", HttpStatus.NOT_FOUND));

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUsername(name);
        int userId = job.getEmployer().getId();

        if (job.getIsPrivate() && user != null && user.getUserId() != userId) {
            throw new ApiException("Bài viết tạm thời không có sẵn với mọi người", HttpStatus.FORBIDDEN);
        }

        return job;
    }

    @Transactional
    public Job create(JobDto jobDto, List<Address> addresses, List<IndustrySpecialization> industrySpecializations) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Job job = Job
                .builder()
                .employer(employerService.findById(user.getUserId()))
                .duration(jobDto.getDuration())
                .title(jobDto.getTitle())
                .ageFrom(jobDto.getAgeFrom())
                .ageTo(jobDto.getAgeTo())
                .salaryFrom(jobDto.getSalaryFrom())
                .salaryTo(jobDto.getSalaryTo())
                .sex(jobDto.getSex())
                .degree(jobDto.getDegree())
                .workingForm(jobDto.getWorkingForm())
                .experience(jobDto.getExperience())
                .description(jobDto.getDescription())
                .requirement(jobDto.getRequirement())
                .addresses(new HashSet<>(addresses))
                .quantity(jobDto.getQuantity())
                .industrySpecializations(new HashSet<>(industrySpecializations))
                .isPrivate(jobDto.isPrivate())
                .build();
        try {
            job = jobRepository.save(job);
            return job;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException("Có lỗi xảy ra khi tạo job. Vui lòng thử lại!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResultCount<Job> search(String searchContent, String industry, String province, Integer page, Integer limit) {
        Page<Job> jobPage;
        Pageable pageable = (page == null || limit == null || page < 1 || limit < 1)
                ? Pageable.unpaged()
                : PageRequest.of(page - 1, limit, Sort.Direction.DESC,"updatedAt");

        jobPage = jobRepository.findByProvinceAndIndustryAndMore(searchContent,industry,province,pageable);
        try {
            List<Job> posts = jobPage.toList();
            long count = jobPage.getTotalElements();

            return new ResultCount<>(posts, count);
        } catch (Exception e) {
            throw new ApiException("Lỗi! không thể tim kiếm", HttpStatus.FORBIDDEN);
        }
    }

}
