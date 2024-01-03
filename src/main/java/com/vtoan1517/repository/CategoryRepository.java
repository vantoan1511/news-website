package com.vtoan1517.repository;

import com.vtoan1517.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCode(String code);
}
