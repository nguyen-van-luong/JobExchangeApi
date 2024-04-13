package com.fjs.jobexchange.controllers;

import com.fjs.jobexchange.dtos.ProvinceDto;
import com.fjs.jobexchange.dtos.SkillDto;
import com.fjs.jobexchange.models.Province;
import com.fjs.jobexchange.models.Skill;
import com.fjs.jobexchange.services.ProvinceService;
import com.fjs.jobexchange.services.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skill")
public class SkillController {
    private final SkillService skillService;
    @PostMapping("/creates")
    public ResponseEntity<?> creates(@Valid @RequestBody List<SkillDto> skillDtos) {
        List<Skill> createsSkill = skillService.creates(skillDtos);
        return new ResponseEntity<>(createsSkill, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<Skill> skills = skillService.findAll();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

}
