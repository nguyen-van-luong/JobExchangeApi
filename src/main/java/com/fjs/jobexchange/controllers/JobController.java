package com.fjs.jobexchange.controllers;

import com.fjs.jobexchange.dtos.JobDto;
import com.fjs.jobexchange.models.Address;
import com.fjs.jobexchange.models.IndustrySpecialization;
import com.fjs.jobexchange.models.Job;
import com.fjs.jobexchange.services.AddressService;
import com.fjs.jobexchange.services.IndustrySpecializationService;
import com.fjs.jobexchange.services.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableMethodSecurity
@RestController
@RequiredArgsConstructor
@RequestMapping("/job")
public class JobController {

    private final JobService jobService;
    private final AddressService addressService;
    private final IndustrySpecializationService industrySpecializationService;

    @PreAuthorize("hasRole('employer')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody JobDto jobDto) {
        List<Address> addresses = addressService.creates(jobDto.getAddresses());
        List<IndustrySpecialization> industrySpecializations = industrySpecializationService.creates(jobDto.getIndustrySpecializations());
        Job job = jobService.create(jobDto, addresses, industrySpecializations);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

}
