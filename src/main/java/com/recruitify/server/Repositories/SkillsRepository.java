package com.recruitify.server.Repositories;

import com.recruitify.server.Entities.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillsRepository extends JpaRepository<Skills, Long> {
    List<Skills> findByIdIn(List<Long> id);
}
