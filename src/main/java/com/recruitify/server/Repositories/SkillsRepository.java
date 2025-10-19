package com.recruitify.server.Repositories;

import com.recruitify.server.Entities.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepository extends JpaRepository<Long, Skills> {
}
