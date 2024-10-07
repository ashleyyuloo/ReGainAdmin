package com.regain.adminzn.service.impl;

import com.regain.adminzn.dto.ReportDTO;
import com.regain.adminzn.model.EQListingReport;
import com.regain.adminzn.model.ListingReport;
import com.regain.adminzn.model.User;
import com.regain.adminzn.model.UserListingReport;
import com.regain.adminzn.repository.EQListingReportRepository;
import com.regain.adminzn.repository.ListingReportRepository;
import com.regain.adminzn.repository.UserListingReportRepository;
import com.regain.adminzn.repository.UserRepository;
import com.regain.adminzn.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final EQListingReportRepository eqListingReportRepository;
    private final ListingReportRepository listingReportRepository;
    private final UserListingReportRepository userListingReportRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportServiceImpl(EQListingReportRepository eqListingReportRepository,
                             ListingReportRepository listingReportRepository,
                             UserListingReportRepository userListingReportRepository, UserRepository userRepository) {
        this.eqListingReportRepository = eqListingReportRepository;
        this.listingReportRepository = listingReportRepository;
        this.userListingReportRepository = userListingReportRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<ReportDTO> getAllReports() {
        List<ReportDTO> reports = new ArrayList<>();
        reports.addAll(getEqListingReports());
        reports.addAll(getListingReports());
        reports.addAll(getUserReports());
        return reports;
    }

    @Override
    public List<ReportDTO> getUserReports() {
        return userListingReportRepository.findAll()
                .stream()
                .map(this::convertUserListingReportToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ReportDTO> getListingReports() {
        return listingReportRepository.findAll()
                .stream()
                .map(this::convertListingReportToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportDTO> getEqListingReports() {
        return eqListingReportRepository.findAll()
                .stream()
                .map(this::convertEqListingReportToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportDTO> getReportsByReporterId(int reporterId) {
        List<ReportDTO> userReports = userListingReportRepository.findById(reporterId)
                .stream()
                .map(this::convertUserListingReportToDTO)
                .collect(Collectors.toList());

        List<ReportDTO> listingReports = listingReportRepository.findById(reporterId)
                .stream()
                .map(this::convertListingReportToDTO)
                .collect(Collectors.toList());

        List<ReportDTO> eqListingReports = eqListingReportRepository.findById(reporterId)
                .stream()
                .map(this::convertEqListingReportToDTO)
                .collect(Collectors.toList());

        List<ReportDTO> allReports = new ArrayList<>();
        allReports.addAll(userReports);
        allReports.addAll(listingReports);
        allReports.addAll(eqListingReports);

        return allReports;
    }
    

    @Override
    public List<ReportDTO> getReportsByReportedUserId(int reportedUserId) {
        List<ReportDTO> userReports = userListingReportRepository.findByReportedUserId(reportedUserId)
                .stream()
                .map(this::convertUserListingReportToDTO)
                .collect(Collectors.toList());

        List<ReportDTO> allReports = new ArrayList<>();
        allReports.addAll(userReports);
        return allReports;
    }

    @Override
    public List<ReportDTO> getLatestReports() {
        List<ReportDTO> allReports = new ArrayList<>();
        allReports.addAll(getEqListingReports());
        allReports.addAll(getListingReports());
        allReports.addAll(getUserReports());

        // Sort by timestamp in descending order and limit to 20
        return allReports.stream()
                        .sorted(Comparator.comparing(ReportDTO::getTimestamp).reversed())
                        .limit(20)
                        .collect(Collectors.toList());
    }


    public ReportDTO getReportById(int reportId, String reportType) {
        if ("Product".equals(reportType)) {
            return convertListingReportToDTO(listingReportRepository.findById(reportId).orElse(null));
        } else if ("Equipment".equals(reportType)) {
            return convertEqListingReportToDTO(eqListingReportRepository.findById(reportId).orElse(null));
        } else if ("User".equals(reportType)) {
            return convertUserListingReportToDTO(userListingReportRepository.findById(reportId).orElse(null));
        } else {
            throw new IllegalArgumentException("Invalid report type: " + reportType);
        }
    }
    
    @Override
public void updateReportStatus(ReportDTO report) {
    if ("Product".equals(report.getReportType())) {
        ListingReport listingReport = listingReportRepository.findById(report.getReportId()).orElse(null);
        if (listingReport != null) {
            listingReport.setStatus(report.getStatus());
            listingReport.setReportReply(report.getReportReply());
            listingReportRepository.save(listingReport);
        }
    } else if ("Equipment".equals(report.getReportType())) {
        EQListingReport eqListingReport = eqListingReportRepository.findById(report.getReportId()).orElse(null);
        if (eqListingReport != null) {
            eqListingReport.setStatus(report.getStatus());
            eqListingReport.setReportReply(report.getReportReply());
            eqListingReportRepository.save(eqListingReport);
        }
    } else if ("User".equals(report.getReportType())) {
        UserListingReport userListingReport = userListingReportRepository.findById(report.getReportId()).orElse(null);
        if (userListingReport != null) {
            userListingReport.setStatus(report.getStatus());
            userListingReport.setReportReply(report.getReportReply());
            userListingReportRepository.save(userListingReport);
        }
    } else {
        throw new IllegalArgumentException("Invalid report type: " + report.getReportType());
    }
}

    public void addPenaltyPoints(ReportDTO report, int penaltyPoints) {
        if ("Product".equals(report.getReportType())) {
            ListingReport listingReport = listingReportRepository.findById(report.getReportId()).orElse(null);
            if (listingReport != null) {
                User seller = listingReport.getProduct().getSeller();
                seller.setPenaltyPoints(seller.getPenaltyPoints() + penaltyPoints);
                userRepository.save(seller); 
            }
        } else if ("Equipment".equals(report.getReportType())) {
            EQListingReport eqListingReport = eqListingReportRepository.findById(report.getReportId()).orElse(null);
            if (eqListingReport != null) {
                User renter = eqListingReport.getEquipment().getRenter();
                renter.setPenaltyPoints(renter.getPenaltyPoints() + penaltyPoints);
                userRepository.save(renter); 
            }
        } else if ("User".equals(report.getReportType())) {
            UserListingReport userListingReport = userListingReportRepository.findById(report.getReportId()).orElse(null);
            if (userListingReport != null) {
                User reportedUser = userListingReport.getReportedUser();
                reportedUser.setPenaltyPoints(reportedUser.getPenaltyPoints() + penaltyPoints);
                userRepository.save(reportedUser); 
            }
        } else {
            throw new IllegalArgumentException("Invalid report type: " + report.getReportType());
        }
    }

   
    private ReportDTO convertUserListingReportToDTO(UserListingReport userListingReport) {
        return ReportDTO.builder()
                .reportId(userListingReport.getId())
                .reporter(userListingReport.getReporter().getUserName())
                .reported(userListingReport.getReportedUser().getUserName())
                .reportedUserId(userListingReport.getReportedUser().getId())
                .category(userListingReport.getCategory().getCategory())
                .timestamp(userListingReport.getTimestamp())
                .status(userListingReport.getStatus())
                .reportType("User")
                .details(userListingReport.getDetails())
                .build();
    }

    private ReportDTO convertListingReportToDTO(ListingReport listingReport) {
        return ReportDTO.builder()
                .reportId(listingReport.getId())
                .reporter(listingReport.getReporter().getUserName())
                .reported(listingReport.getProduct().getProductName())
                .reportedProductId(listingReport.getProduct().getId())
                .category(listingReport.getCategory().getCategory())
                .timestamp(listingReport.getTimestamp())
                .status(listingReport.getStatus())
                .reportType("Product")
                .details(listingReport.getDetails())
                .build();
    }

    private ReportDTO convertEqListingReportToDTO(EQListingReport eqListingReport) {
        return ReportDTO.builder()
            .reportId(eqListingReport.getId())
            .reporter(eqListingReport.getReporter().getUserName())
            .reported(eqListingReport.getEquipment().getName())
            .reportedEquipmentId(eqListingReport.getEquipment().getId()) 
            .category(eqListingReport.getCategory().getCategory())
            .timestamp(eqListingReport.getTimestamp())
            .status(eqListingReport.getStatus())
            .reportType("Equipment")
            .details(eqListingReport.getDetails())
            .build();
    }    
    

}
