package com.fjs.jobexchange.controllers;

import com.fjs.jobexchange.dtos.SpecializationDto;
import com.fjs.jobexchange.models.Province;
import com.fjs.jobexchange.models.Specialization;
import com.fjs.jobexchange.services.SpecializationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return new ResponseEntity<>(specializationService.findByIndustryId(id), HttpStatus.OK);
    }
}
