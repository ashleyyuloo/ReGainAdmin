package com.regain.adminzn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.regain.adminzn.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleType(String name);
}
