package com.regain.adminzn.service;

import java.util.List;
import com.regain.adminzn.dto.ReportDTO;

public interface ReportService {
    List<ReportDTO> getAllReports();
    List<ReportDTO> getUserReports();
    List<ReportDTO> getListingReports();
    List<ReportDTO> getEqListingReports();
    List<ReportDTO> getReportsByReporterId(int reporterId);
    List<ReportDTO> getReportsByReportedUserId(int reportedUserId);
    ReportDTO getReportById(int reportId, String reportType);
    void updateReportStatus(ReportDTO report);
    List<ReportDTO> getLatestReports();
    void addPenaltyPoints(ReportDTO report, int penaltyPoints);
}
