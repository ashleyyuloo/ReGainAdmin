package com.regain.adminzn.controller;

import java.util.List;

import com.regain.adminzn.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.regain.adminzn.service.*;

@Controller
public class ReportsController {

    private final ReportService reportService;
    private final UserService userService;
    private final EquipmentService equipmentService;
    private final ProductService productService;

    @Autowired
    public ReportsController(ReportService reportService, UserService userService, EquipmentService equipmentService, ProductService productService) {
        this.reportService = reportService;
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.productService = productService;  
    }

    @GetMapping("/reports")
    public String showReports(Model model) {
        List<ReportDTO> allReports = reportService.getAllReports();
        List<ReportDTO> userReports = reportService.getUserReports();
        List<ReportDTO> listingReports = reportService.getListingReports();
        List<ReportDTO> equipmentReports = reportService.getEqListingReports();

        model.addAttribute("allReports", allReports);
        model.addAttribute("userReports", userReports);
        model.addAttribute("listingReports", listingReports);
        model.addAttribute("equipmentReports", equipmentReports);
        model.addAttribute("reports", allReports);
        return "reports/index";
    }

    @GetMapping("/reports/userreportselect")
    public String viewUserReport(@RequestParam("reportId") int reportId, Model model) {
        ReportDTO report = reportService.getReportById(reportId, "User");
        if (report == null) {
            throw new RuntimeException("Report not found with id: " + reportId);
        }
        model.addAttribute("report", report);

        UserDTO reportedUser = userService.getUserById(report.getReportedUserId());
        model.addAttribute("user", reportedUser);

        return "reports/selected-user";
    }



    @GetMapping("/reports/equipmentreportselect")
    public String viewEquipmentReport(@RequestParam("reportId") int reportId, Model model) {
        ReportDTO report = reportService.getReportById(reportId, "Equipment"); 
        if (report == null) {
            throw new RuntimeException("Report not found with id: " + reportId);
        }
        model.addAttribute("report", report);

        EquipmentDTO equipment = equipmentService.getEquipmentById(report.getReportedEquipmentId());
        model.addAttribute("equipment", equipment);

        return "reports/selected-equipment";
    }

    @GetMapping("/reports/productreportselect")
    public String viewProductReport(@RequestParam("reportId") int reportId, Model model) {
        ReportDTO report = reportService.getReportById(reportId, "Product");
        if (report == null) {
            throw new RuntimeException("Report not found with id: " + reportId);
        }
        model.addAttribute("report", report);

        ProductDTO reportedProduct = productService.getProductById(report.getReportedProductId());
        model.addAttribute("product", reportedProduct);

        return "reports/selected-product";
    }

    @PostMapping("/reports/investigate")
    public String investigateReport(@RequestParam("reportId") int reportId, @RequestParam("reportType") String reportType) {
        ReportDTO report = reportService.getReportById(reportId, reportType);
        if (report == null) {
            throw new RuntimeException("Report not found with id: " + reportId);
        }
        
        report.setStatus("In Progress");
        reportService.updateReportStatus(report);

        if ("User".equals(report.getReportType())) {
            return "redirect:/reports/userreportselect?reportId=" + reportId;
        } else if ("Equipment".equals(report.getReportType())) {
            return "redirect:/reports/equipmentreportselect?reportId=" + reportId;
        } else if ("Product".equals(report.getReportType())) {
            return "redirect:/reports/productreportselect?reportId=" + reportId;
        } else {
            throw new IllegalArgumentException("Invalid report type: " + report.getReportType());
        }
    }


    // @PostMapping("/reports/resolved")
    // public String resolveReport(@RequestParam("reportId") int reportId, @RequestParam("reportType") String reportType) {
    //     ReportDTO report = reportService.getReportById(reportId, reportType);
    //     if (report == null) {
    //         throw new RuntimeException("Report not found with id: " + reportId);
    //     }
        
    //     report.setStatus("Resolved");
    //     reportService.updateReportStatus(report);

    //     if ("User".equals(report.getReportType())) {
    //         return "redirect:/reports/userreportselect?reportId=" + reportId;
    //     } else if ("Equipment".equals(report.getReportType())) {
    //         return "redirect:/reports/equipmentreportselect?reportId=" + reportId;
    //     } else if ("Product".equals(report.getReportType())) {
    //         return "redirect:/reports/productreportselect?reportId=" + reportId;
    //     } else {
    //         throw new IllegalArgumentException("Invalid report type: " + report.getReportType());
    //     }
    // }

    // @PostMapping("/reports/resolved/modal")
    // public String resolveReportModal(@RequestParam("reportId") int reportId,
    //                                 @RequestParam("reportType") String reportType,
    //                                 @RequestParam("resolutionMessage") String resolutionMessage) {
    //     ReportDTO report = reportService.getReportById(reportId, reportType);
    //     if (report == null) {
    //         throw new RuntimeException("Report not found with id: " + reportId);
    //     }

    //     report.setStatus("Resolved");
    //     report.setReportReply(resolutionMessage); 
    //     reportService.updateReportStatus(report);

    //     if ("User".equals(report.getReportType())) {
    //         return "redirect:/reports/userreportselect?reportId=" + reportId;
    //     } else if ("Equipment".equals(report.getReportType())) {
    //         return "redirect:/reports/equipmentreportselect?reportId=" + reportId;
    //     } else if ("Product".equals(report.getReportType())) {
    //         return "redirect:/reports/productreportselect?reportId=" + reportId;
    //     } else {
    //         throw new IllegalArgumentException("Invalid report type: " + report.getReportType());
    //     }
    // }

    @PostMapping("/reports/resolved/modal")
public String resolveReportModal(@RequestParam("reportId") int reportId,
                                 @RequestParam("reportType") String reportType,
                                 @RequestParam("resolutionMessage") String resolutionMessage) {
    ReportDTO report = reportService.getReportById(reportId, reportType);
    if (report == null) {
        throw new RuntimeException("Report not found with id: " + reportId);
    }

    report.setStatus("Resolved");
    report.setReportReply(resolutionMessage); 
    reportService.updateReportStatus(report);

    // Assign penalty points based on the report category
    int penaltyPoints = 0;
    switch (report.getCategory()) {
        case "Suspicious Account":
            penaltyPoints = 2;
            break;
        case "Fake Location":
            penaltyPoints = 1;
            break;
        case "Item Wrongly Categorized":
            penaltyPoints = 2;
            break;
        case "Selling Prohibited Items":
            penaltyPoints = 2;
            break;
        case "Mispriced Listings":
            penaltyPoints = 2;
            break;
        case "Offensive Behavior/Content":
            penaltyPoints = 2;
            break;
        case "Phishing Scammer":
            penaltyPoints = 2;
            break;
        case "Cancelling On Deal":
            penaltyPoints = 2;
            break;
        default:
            throw new IllegalArgumentException("Invalid report category: " + report.getCategory());
    }
    reportService.addPenaltyPoints(report, penaltyPoints);

    if ("User".equals(report.getReportType())) {
        return "redirect:/reports/userreportselect?reportId=" + reportId;
    } else if ("Equipment".equals(report.getReportType())) {
        return "redirect:/reports/equipmentreportselect?reportId=" + reportId;
    } else if ("Product".equals(report.getReportType())) {
        return "redirect:/reports/productreportselect?reportId=" + reportId;
    } else {
        throw new IllegalArgumentException("Invalid report type: " + report.getReportType());
    }
}


    @PostMapping("/reports/revert")
    public String revertReport(@RequestParam("reportId") int reportId, @RequestParam("reportType") String reportType) {
    ReportDTO report = reportService.getReportById(reportId, reportType);
    if (report == null) {
        throw new RuntimeException("Report not found with id: " + reportId);
    }
    
    report.setStatus("Reverted");
    reportService.updateReportStatus(report);
    
    if ("User".equals(report.getReportType())) {
        return "redirect:/reports/userreportselect?reportId=" + reportId;
    } else if ("Equipment".equals(report.getReportType())) {
        return "redirect:/reports/equipmentreportselect?reportId=" + reportId;
    } else if ("Product".equals(report.getReportType())) {
        return "redirect:/reports/productreportselect?reportId=" + reportId;
    } else {
        throw new IllegalArgumentException("Invalid report type: " + report.getReportType());
    }
}


}

