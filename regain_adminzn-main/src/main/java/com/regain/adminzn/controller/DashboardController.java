package com.regain.adminzn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.regain.adminzn.dto.ReportDTO;
import com.regain.adminzn.service.OrderService;
import com.regain.adminzn.service.ReportService;
import com.regain.adminzn.service.UserService;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

     @Autowired
    private ReportService reportService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        int totalUsers = userService.getTotalUsers();
        int totalTransactions = orderService.getTotalTransactions();
        double averageTransactionValue = orderService.getAverageTransactionValue();

        List<ReportDTO> latestReports = reportService.getLatestReports();

        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalTransactions", totalTransactions);
        model.addAttribute("averageTransactionValue", averageTransactionValue);
        model.addAttribute("latestReports", latestReports);
        model.addAttribute("activePage", "dashboard");
        
        return "dashboard/index";
    }
}
