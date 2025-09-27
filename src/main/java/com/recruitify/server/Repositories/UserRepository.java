package com.recruitify.server.Repositories;

import com.recruitify.server.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Long id(long id);
}
