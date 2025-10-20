package com.recruitify.server.Dtos.Request;

import com.recruitify.server.Entities.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateJobRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    private String responsibilities;

    private String requirement;

    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    private BigDecimal salary;

    private String benefit;

    @NotNull(message = "Company is required")
    private Company company;

    private EmploymentType employmentType;

    private ExperienceLevel experienceLevel;

    private Category category;

    private String wardCode;

    private List<Skills> skill;
}