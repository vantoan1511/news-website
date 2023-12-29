package com.vtoan1517.repository;

import com.vtoan1517.entity.AccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRepository extends JpaRepository<AccessEntity, Long> {
    AccessEntity findByCode(String code);
}
