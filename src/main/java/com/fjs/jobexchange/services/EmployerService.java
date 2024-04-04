package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.ApiException;
import com.fjs.jobexchange.models.Employer;
import com.fjs.jobexchange.repositories.EmployerRepository;
import com.fjs.jobexchange.repositories.IndustryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;

    public Employer findById(Integer id){
        return employerRepository.findById(id)
                .orElseThrow(() -> new ApiException("Employer không tồn tại!", HttpStatus.BAD_REQUEST));
    }

}
