package com.vtoan1517.repository;

import com.vtoan1517.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByCode(String code);
}
