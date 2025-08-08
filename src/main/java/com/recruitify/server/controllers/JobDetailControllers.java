package com.recruitify.server.controllers;

import com.recruitify.server.entities.JobDetail;
import com.recruitify.server.services.JobDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobDetailControllers {
    private final JobDetailService jobDetailService;
    public JobDetailControllers(JobDetailService _jobDetailService)
    {
        jobDetailService = _jobDetailService;
    }
    @GetMapping("/api/jobdetail")
    public List<JobDetail> getJobDetail() {
        return jobDetailService.handleGetJobDetails();
    }
    @PostMapping("/api/jobdetail")
    public JobDetail createJobDetail(@RequestBody JobDetail jobDetail)
    {
        return jobDetailService.createJobDetail(jobDetail);
    }
}
