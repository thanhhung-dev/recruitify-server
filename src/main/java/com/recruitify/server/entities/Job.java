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
    private String jobId;

    @Column(name ="job_title", length = 255, nullable = false)
    private String jobTitle;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "job_level_id")
    private Long jobLevelId;

    @Column(name = "job_type_id")
    private Long jobTypeId;

    @Column(name = "over_view", columnDefinition = "TEXT")
    private String overView;

    @Column(name = "salary", precision = 10, scale = 2)
    private BigDecimal salary;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Instant updatedAt;
    @Column(name = "created_by", length = 100)
    private String create_by;

    @PrePersist
    public void onCreate()
    {
        if(createdAt == null)
        {
            createdAt = Instant.now();
            updatedAt = Instant.now();
        }
        if(updatedAt == null)
        {
            updatedAt = Instant.now();
        }
        if(create_by == null)
        {
            create_by = "HungPig";
        }
    }
    @PreUpdate
    public void onUpdate()
    {
        updatedAt = Instant.now();
    }

    //Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_level_id", insertable = false, updatable = false)
    private JobLevel jobLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_type_id", insertable = false, updatable = false)
    private JobType jobType;

}
