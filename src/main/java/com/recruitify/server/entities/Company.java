package com.recruitify.server.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Data
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

    @Column(name = "image")
    private String image;

    @Column(name = "phone")
    private String phone;

    @Column(name = "industry")
    private String industry;

    @Column(name = "company_type")
    private String companyType;

    @Column(name = "company_size")
    private String companySize;

    @Column(name = "founder_year")
    private Integer founderYear;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Instant updatedAt;
    @Column(name = "created_by", length = 100)
    private String createdBy;

    @PrePersist
    public void onCreate() {
        if (createdBy == null) {
            createdBy = "HungPig";
        }
    }
}
