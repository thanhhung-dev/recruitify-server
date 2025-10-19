package com.recruitify.server.Repositories;

import com.recruitify.server.Entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Long, Ward> {
}
