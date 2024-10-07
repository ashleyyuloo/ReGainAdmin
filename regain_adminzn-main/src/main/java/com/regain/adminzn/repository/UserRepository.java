package com.regain.adminzn.repository;

import com.regain.adminzn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.role.roleType = :roleType")
    List<User> findByRoleType(@Param("roleType") String roleType);
}
