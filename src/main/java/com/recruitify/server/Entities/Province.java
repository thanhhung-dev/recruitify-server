package com.recruitify.server.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "provinces")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {

    @Id
    private String code;

    private String name;
    private String slug;
    private String type;

    @Column(name = "name_with_type")
    private String nameWithType;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "administrative_unit_id")
    private Integer administrativeUnitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrative_unit_id", insertable = false, updatable = false)
    private AdministrativeUnit administrativeUnit;

}

