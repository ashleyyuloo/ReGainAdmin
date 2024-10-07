package com.regain.adminzn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.regain.adminzn.model.ReportCategory;

public interface ReportCategoryRepository extends JpaRepository<ReportCategory, Integer> {
}
