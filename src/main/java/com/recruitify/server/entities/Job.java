package com.recruitify.server.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "job")
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long jobId;

    @Column(name ="job_title", length = 255, nullable = false)
    private String jobTitle;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "job_level_id")
    private Long jobLevelId;

    @Column(name = "job_type_id")
    private Long jobTypeId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "over_view", columnDefinition = "TEXT")
    private String overView;

    @Column(name = "salary", precision = 10, scale = 2)
    private BigDecimal salary;

    @Column(name = "requirements_priority", columnDefinition = "TEXT")
    private String requirementsPriority;

    @Column(name = "requirements_experience", columnDefinition = "TEXT")
    private String requirementsExperience;

    @Column(name = "requirements_skills", columnDefinition = "TEXT")
    private String requirementsSkills;

    @Column(name = "is_hidden")
    private Boolean isHidden = false;

    //Relationship
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_level_id", insertable = false, updatable = false)
    private JobLevel jobLevel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_type_id", insertable = false, updatable = false)
    private JobType jobType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

    @Column(name = "created_at", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Instant updatedAt;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;
    // Calculated property (not stored in database)

    @PrePersist
    protected void onCreate() {
        if (createdBy == null)
        {
            createdBy = "HungThanh";
        }
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (updatedAt == null) {
            updatedAt = Instant.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedBy = "HungThanh";
        updatedAt = Instant.now();
    }

}
