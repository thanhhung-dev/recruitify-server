package com.recruitify.server.repositories;

import com.recruitify.server.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String name);
}
