package com.recruitify.server.Controllers;

import com.recruitify.server.Dtos.Response.Job.JobResponse;
import com.recruitify.server.Entities.Company;
import com.recruitify.server.Entities.Job;
import com.recruitify.server.Services.Implementations.JobServiceImpl;
import com.recruitify.server.Util.Annotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/")
@RestController
@RequiredArgsConstructor
public class JobControllers {
    private final JobServiceImpl jobService;

    @GetMapping("/jobs")
    @ApiMessage("Get All Job")
    public ResponseEntity<List<JobResponse>> getAllJob()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.jobService.getAllJob());
    }

    @PostMapping("/jobs")
    @ApiMessage("Create a job")
    public ResponseEntity<JobResponse> createCompany(@RequestBody Job job) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.jobService.createJob(job));
    }
}
