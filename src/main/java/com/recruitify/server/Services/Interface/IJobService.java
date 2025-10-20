package com.recruitify.server.Services.Interface;

import com.recruitify.server.Dtos.Request.CreateJobRequest;
import com.recruitify.server.Dtos.Response.Job.JobResponse;

import java.util.List;

public interface IJobService {
    List<JobResponse>  getAllJob();
    JobResponse createJob(CreateJobRequest request);
}
