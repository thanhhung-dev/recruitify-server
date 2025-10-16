package com.recruitify.server.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrative_regions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministrativeRegion {
    @Id
    private Integer id;
    private String name;
    private String name_en;
    private String code_name;
    private String code_name_en;
}
