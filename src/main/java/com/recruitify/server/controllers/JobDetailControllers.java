package com.recruitify.server.controllers;

import com.recruitify.server.entities.JobDetail;
import com.recruitify.server.services.JobDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobDetailControllers {
    private final JobDetailService jobDetailService;
    @GetMapping("/api/jobdetail")
    public ResponseEntity<List<JobDetail>> getJobDetail() {
        return ResponseEntity.status(HttpStatus.OK).body(this.jobDetailService.handleGetJobDetails());
    }
    @PostMapping("/api/jobdetail")
    public ResponseEntity<JobDetail> createJobDetail(@RequestBody JobDetail jobDetail) {
        JobDetail createdJobDetail = jobDetailService.createJobDetail(jobDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJobDetail);
    }
}
