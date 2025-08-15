package com.recruitify.server.services;

import com.recruitify.server.entities.Job;
import com.recruitify.server.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    JobRepository jobRepository;
    public JobService(JobRepository _jobRepository)
    {
        jobRepository = _jobRepository;
    }
    public Job findJobById(long id)
    {
        Optional<Job> jobDetail = jobRepository.findById(id);
        return jobDetail.orElse(null);
    }
    public List<Job> handleGetJobDetails()
    {
        return this.jobRepository.findAll();
    }
    public Job createJobDetail(Job job)
    {
        return this.jobRepository.save(job);
    }
    public Job updateJobDetail(Job job)
    {
        return null;
    }
}
