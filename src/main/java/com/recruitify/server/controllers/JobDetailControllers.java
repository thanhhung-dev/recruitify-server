package com.recruitify.server.controllers;

import com.recruitify.server.entities.Job;
import com.recruitify.server.services.JobService;
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
    private final JobService jobService;

    @GetMapping("/api/jobdetail")
    public ResponseEntity<List<Job>> getJobDetail() {
        return ResponseEntity.status(HttpStatus.OK).body(this.jobService.handleGetJobDetails());
    }
    @PostMapping("/api/jobdetail")
    public ResponseEntity<Job> createJobDetail(@RequestBody Job job) {
        Job createdJob = jobService.createJobDetail(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
    }
}
