package com.fjs.jobexchange.controllers;

import com.fjs.jobexchange.dtos.ProvinceDto;
import com.fjs.jobexchange.models.Industry;
import com.fjs.jobexchange.models.Province;
import com.fjs.jobexchange.services.ProvinceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/province")
public class ProvinceController {
    private final ProvinceService provinceService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ProvinceDto provinceDto) {
        Province createProvince = provinceService.create(provinceDto);
        return new ResponseEntity<>(createProvince, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<Province> provinces = provinceService.findAll();
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }

}
