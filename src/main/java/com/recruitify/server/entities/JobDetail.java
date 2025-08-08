package com.recruitify.server.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
@Entity
@Table(name = "job_details")
@Data
public class JobDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id", updatable = false, nullable = false)
    private Long jobId;

    @Column(name ="job_title", length = 255)
    private String jobTitle;

    private String image;

    @Column(name = "over_view", columnDefinition = "TEXT")
    private String overView;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(nullable = false)
    private int salary;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Instant updatedAt;

    private String create_by;
    private String update_by;

    @PrePersist
    public void onCreate()
    {
        if(createdAt == null)
        {
            createdAt = Instant.now();
        }
        if(updatedAt == null)
        {
            updatedAt = Instant.now();
        }
    }
    @PreUpdate
    public void onUpdate()
    {
        updatedAt = Instant.now();
    }
}
