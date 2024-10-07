package com.regain.adminzn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.regain.adminzn.model.Admin;
import com.regain.adminzn.model.Role;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUsername(String username);
    Role findRoleById(int id);
}
