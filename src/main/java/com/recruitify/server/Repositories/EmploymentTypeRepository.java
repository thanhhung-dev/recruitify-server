package com.recruitify.server.Repositories;

import com.recruitify.server.Entities.EmploymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, Long> {
}
