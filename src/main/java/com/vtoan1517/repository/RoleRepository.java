package com.vtoan1517.repository;

import com.vtoan1517.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByCode(String code);
}
