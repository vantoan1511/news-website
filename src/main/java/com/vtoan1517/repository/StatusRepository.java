package com.vtoan1517.repository;

import com.vtoan1517.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
    StatusEntity findByCode(String code);
}
