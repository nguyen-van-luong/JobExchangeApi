package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.ApiException;
import com.fjs.jobexchange.dtos.JobDto;
import com.fjs.jobexchange.dtos.ProvinceDto;
import com.fjs.jobexchange.models.Address;
import com.fjs.jobexchange.models.IndustrySpecialization;
import com.fjs.jobexchange.models.Job;
import com.fjs.jobexchange.models.Province;
import com.fjs.jobexchange.repositories.JobRepository;
import com.fjs.jobexchange.repositories.ProvinceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

    @Transactional
    public Job create(JobDto jobDto, List<Address> addresses, List<IndustrySpecialization> industrySpecializations) {
        Job job = Job
                .builder()
                .employer(employerService.findById(jobDto.getEmployerId()))
                .duration(jobDto.getDuration())
                .title(jobDto.getTitle())
                .ageFrom(jobDto.getAgeFrom())
                .ageTo(jobDto.getAgeTo())
                .salary(jobDto.getSalary())
                .experience(jobDto.getExperience())
                .description(jobDto.getDescription())
                .requirement(jobDto.getRequirement())
                .addresses(new HashSet<>(addresses))
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
}
