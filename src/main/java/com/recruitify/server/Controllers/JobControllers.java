package com.recruitify.server.Controllers;

import com.recruitify.server.Util.Annotation.ApiMessage;
import com.recruitify.server.Entities.Job;
import com.recruitify.server.Services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class JobControllers {
    private final JobService jobService;

    @PostMapping("")
    @ApiMessage("Create a job")
    public ResponseEntity<Job> create( @RequestBody Job job){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.jobService.createJob(job)
        );
    }
    @GetMapping("")
    @ApiMessage("fetch all Job")
    public ResponseEntity<List<Job>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.jobService.handleGetJob());
    }
}
