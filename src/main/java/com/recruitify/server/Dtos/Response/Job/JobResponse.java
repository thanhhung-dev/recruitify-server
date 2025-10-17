package com.recruitify.server.Dtos.Response.Job;

import com.recruitify.server.Entities.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String responsibilities;
    private String requirement;
    private BigDecimal salary;
    private String benefit;

    // Related entities as simple DTOs
    private Company company;
    private EmploymentType employmentType;
    private ExperienceLevel experienceLevel;
    private Category category;
    private List<Skills> skills;
    private Ward ward;

    // Audit fields
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
}
