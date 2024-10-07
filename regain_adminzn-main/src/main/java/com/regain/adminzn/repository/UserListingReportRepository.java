package com.regain.adminzn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.regain.adminzn.model.UserListingReport;

public interface UserListingReportRepository extends JpaRepository<UserListingReport, Integer> {
    List<UserListingReport> findByReportedUserId(int reportedUserId);
}
