package com.vtoan1517.repository;

import com.vtoan1517.entity.Access;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRepository extends JpaRepository<Access, Long> {
    Access findByCode(String code);
}
