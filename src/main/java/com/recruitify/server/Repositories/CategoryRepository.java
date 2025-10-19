package com.recruitify.server.Repositories;

import com.recruitify.server.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Long, Category> {
}
