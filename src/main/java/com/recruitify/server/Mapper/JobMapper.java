package com.recruitify.server.Mapper;

import com.recruitify.server.Dtos.Response.Job.JobResponse;
import com.recruitify.server.Entities.Job;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobMapper {
    public JobResponse toResponseJob(Job job) {
        JobResponse dto = new JobResponse();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setResponsibilities(job.getResponsibilities());
        dto.setRequirement(job.getRequirement());
        dto.setSalary(job.getSalary());
        dto.setBenefit(job.getBenefit());
        dto.setCreatedAt(job.getCreatedAt());
        dto.setUpdatedAt(job.getUpdatedAt());
        dto.setCreatedBy(job.getCreatedBy());
        dto.setUpdatedBy(job.getUpdatedBy());
        return dto;
    }
}
