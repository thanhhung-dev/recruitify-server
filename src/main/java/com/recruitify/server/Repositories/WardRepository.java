package com.recruitify.server.Repositories;

import com.recruitify.server.Entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward, String> {
    Optional<Ward> findByCode(String code);
}
