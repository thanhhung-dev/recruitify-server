package com.recruitify.server.services;

import com.recruitify.server.entities.Company;
import com.recruitify.server.entities.Job;
import com.recruitify.server.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    public Job findJobById(long id)
    {
        Optional<Job> jobDetail = jobRepository.findById(id);
        return jobDetail.orElse(null);
    }
    public List<Job> handleGetJob()
    {
        return this.jobRepository.findAll();
    }
    public Job createJob(Job job)
    {
        return this.jobRepository.save(job);
    }
    public void deleteJob(Long id){
    }
}
