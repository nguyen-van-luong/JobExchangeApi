package com.fjs.jobexchange.controllers;

import com.fjs.jobexchange.dtos.ApplicationDto;
import com.fjs.jobexchange.dtos.CVDto;
import com.fjs.jobexchange.models.Application;
import com.fjs.jobexchange.models.Bookmark;
import com.fjs.jobexchange.services.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;
    @PreAuthorize("hasRole('student')")
    @PostMapping("/submit/{jobId}")
    public ResponseEntity<?> create(@PathVariable Integer jobId, @RequestBody List<Integer> cvIds) {
        applicationService.submitCVs(cvIds, jobId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{studentId}/{jobId}")
    public ResponseEntity<?> findByStudentIdAndJobId(@PathVariable Integer studentId, @PathVariable Integer jobId) {
        List<Application> application=  applicationService.findByJobIdAndStudentId(studentId,jobId);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PostMapping("/delete/{studentId}/{jobId}")
    public ResponseEntity<?> delete(@PathVariable Integer studentId, @PathVariable Integer jobId) {
        applicationService.delete(studentId, jobId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('employer')")
    @GetMapping("/search")
    public ResponseEntity<?> getSearch(
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(required = false, name = "status") String status,
            @RequestParam(required = false, name = "page",defaultValue="1") Integer page,
            @RequestParam(required = false, name = "limit", defaultValue = "10") int limit) {

        return new ResponseEntity<>(applicationService.search( title, status, page, limit), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('employer')")
    @PutMapping("/update")
    public ResponseEntity<?> getSearch(@RequestBody ApplicationDto applicationDto) {
        applicationService.update(applicationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('employer')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody List<ApplicationDto> applications) {
        applicationService.deleteByApplication(applications);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
