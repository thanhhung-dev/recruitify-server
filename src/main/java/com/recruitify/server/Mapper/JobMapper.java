package com.recruitify.server.Mapper;

import com.recruitify.server.Dtos.Response.Job.JobResponse;
import com.recruitify.server.Entities.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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


        String company = Optional.ofNullable(job.getCompany())
                .map(Company::getName)
                .orElse("Unknown");
        dto.setCompany(company);

        String employmentType = Optional.ofNullable(job.getEmploymentType())
                .map(EmploymentType::getName)
                .orElse("Unknown");
        dto.setEmploymentType(employmentType);

        String experienceLevel = Optional.ofNullable(job.getExperienceLevel())
                .map(ExperienceLevel::getName)
                .orElse("Unknown");
        dto.setExperienceLevel(experienceLevel);

        String categoryName = Optional.ofNullable(job.getCategory())
                .map(Category::getName)
                .orElse("Unknown");
        dto.setCategory(categoryName);

        String location = Optional.ofNullable(job.getWard())
                .map(ward -> {
                    String wardName = ward.getFullName();
                    String provinceName = Optional.ofNullable(ward.getProvince())
                            .map(Province::getFullName)
                            .orElse("");
                    return wardName + ", " + provinceName;
                })
                .orElse("Unknown");
        dto.setLocation(location);

        List<String> skills = job.getSkills() == null ?
                Collections.emptyList() :
                job.getSkills().stream().map(Skills::getName).toList();
        dto.setSkills(skills);

        return dto;
    }

    public List<JobResponse> toResponseJobList(List<Job> jobs) {
        if (jobs == null || jobs.isEmpty()) {
            return Collections.emptyList();
        }
        return jobs.stream()
                .map(this::toResponseJob)
                .collect(Collectors.toList());
    }
}
