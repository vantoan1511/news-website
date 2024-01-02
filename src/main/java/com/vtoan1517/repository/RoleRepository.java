package com.vtoan1517.repository;

import com.vtoan1517.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByCode(String code);
}
