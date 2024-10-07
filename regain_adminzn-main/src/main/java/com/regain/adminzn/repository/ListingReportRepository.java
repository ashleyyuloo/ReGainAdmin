package com.regain.adminzn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.regain.adminzn.model.ListingReport;

public interface ListingReportRepository extends JpaRepository<ListingReport, Integer> {
}