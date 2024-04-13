package com.fjs.jobexchange.controllers;

import com.fjs.jobexchange.dtos.CVDto;
import com.fjs.jobexchange.dtos.JobDto;
import com.fjs.jobexchange.models.*;
import com.fjs.jobexchange.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cv")
public class CVController {
    private final CVService cvService;
    private final SkillService skillService;
    private final IndustrySpecializationService industrySpecializationService;

    @PreAuthorize("hasRole('student')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CVDto cvDto) {

        List<Skill> skills = skillService.creates(cvDto.getSkills());
        List<IndustrySpecialization> industrySpecializations = industrySpecializationService.creates(cvDto.getIndustrySpecializations());
        CV cv = cvService.create(cvDto, skills, industrySpecializations);
        return new ResponseEntity<>(cv, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getSearch(
            @RequestParam(required = false, name = "searchContent", defaultValue = "") String searchContent,
            @RequestParam(required = false, name = "industry") String industry,
            @RequestParam(required = false, name = "province") String province,
            @RequestParam(required = false, name = "page",defaultValue="1") Integer page,
            @RequestParam(required = false, name = "limit", defaultValue = "10") int limit) {
        System.out.println(industry);

        return new ResponseEntity<>(cvService.search( searchContent,industry, province, page, limit), HttpStatus.OK);
    }
}
