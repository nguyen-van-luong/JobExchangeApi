package com.fjs.jobexchange.controllers;

import com.fjs.jobexchange.dtos.IndustryDto;
import com.fjs.jobexchange.models.Industry;
import com.fjs.jobexchange.services.IndustryService;
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
@RequestMapping("/industry")
public class IndustryController {
    private final IndustryService industryService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody IndustryDto industryDto) {
       Industry createIndustry = industryService.create(industryDto);
        return new ResponseEntity<>(createIndustry, HttpStatus.OK);
    }
}
