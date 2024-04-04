package com.fjs.jobexchange.controllers;

import com.fjs.jobexchange.dtos.SpecializationDto;
import com.fjs.jobexchange.models.Specialization;
import com.fjs.jobexchange.services.SpecializationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/specialization")
public class SpecializationController {
    private final SpecializationService specializationService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody SpecializationDto specializationDto) {
        Specialization createSpecialization = specializationService.create(specializationDto);
        return new ResponseEntity<>(createSpecialization, HttpStatus.OK);
    }

}
