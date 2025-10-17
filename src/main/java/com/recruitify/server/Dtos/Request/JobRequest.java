package com.recruitify.server.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class JobRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    private String description;
    private String responsibilities;
    private String requirement;
    private BigDecimal salary;
    private String benefit;

    @NotNull(message = "Company ID is required")
    private Long companyId;

    @NotNull(message = "Employment type ID is required")
    private Long employmentTypeId;

    @NotNull(message = "Experience level ID is required")
    private Long experienceLevelId;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    private List<Long> skillIds;
    private String wardCode;
}