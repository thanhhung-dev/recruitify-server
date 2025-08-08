package com.recruitify.server.services;

import com.recruitify.server.entities.JobDetail;
import com.recruitify.server.repositories.JobDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobDetailService {
    JobDetailRepository jobDetailRepository;
    public JobDetailService(JobDetailRepository _jobDetailRepository)
    {
        jobDetailRepository = _jobDetailRepository;
    }
    public List<JobDetail> handleGetJobDetails()
    {
        return this.jobDetailRepository.findAll();
    }
    public JobDetail createJobDetail(JobDetail jobDetail)
    {
        return this.jobDetailRepository.save(jobDetail);
    }
}
