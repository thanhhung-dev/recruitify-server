package com.recruitify.server.Services.Interface;

import com.recruitify.server.Dtos.Response.Job.JobResponse;
import com.recruitify.server.Entities.Job;

import java.util.List;

public interface IJobService {
    List<JobResponse>  getAllJob();
    JobResponse createJob(Job request);
}
