package com.regain.adminzn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.regain.adminzn.model.EQListingReport;

public interface EQListingReportRepository extends JpaRepository<EQListingReport, Integer> {
    List<EQListingReport> findByEquipment_Id(int equipmentId);
}
