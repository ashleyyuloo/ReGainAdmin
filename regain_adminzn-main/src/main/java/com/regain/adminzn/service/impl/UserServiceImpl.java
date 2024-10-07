package com.regain.adminzn.service.impl;

import com.regain.adminzn.dto.ReportDTO;
import com.regain.adminzn.dto.UserDTO;
import com.regain.adminzn.model.User;
import com.regain.adminzn.repository.UserRepository;
import com.regain.adminzn.service.ReportService;
import com.regain.adminzn.service.UserService;
import com.regain.adminzn.service.constants.UserRoleConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReportService reportService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ReportService reportService) {
        this.userRepository = userRepository;
        this.reportService = reportService;
    }

    @Override
    public UserDTO getUserById(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<ReportDTO> reports = reportService.getReportsByReporterId(userId);
            List<ReportDTO> reportedReports = reportService.getReportsByReportedUserId(userId);
            return convertToDTO(user, reports, reportedReports);
        }
        return null;
    }

    private UserDTO convertToDTO(User user, List<ReportDTO> reports, List<ReportDTO> reportedReports) {
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .role(user.getRole() != null ? user.getRole().getRoleType() : null)
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .penaltyPoints(user.getPenaltyPoints())
                .commissionBalance(user.getCommissionBalance())
                .profileImagePath(user.getProfileImagePath())
                .reportedReports(reportedReports)
                .junkshopName(user.getJunkshopName())
                .build();
    
        return userDTO;
    }
    
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(user -> convertToDTO(user, 
                        reportService.getReportsByReporterId(user.getId()), 
                        reportService.getReportsByReportedUserId(user.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getHouseholds() {
        List<User> households = userRepository.findByRoleType(UserRoleConstants.HOUSEHOLD);
        return households.stream()
                .map(user -> convertToDTO(user, 
                        reportService.getReportsByReporterId(user.getId()), 
                        reportService.getReportsByReportedUserId(user.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getJunkShops() {
        List<User> junkShops = userRepository.findByRoleType(UserRoleConstants.JUNK_SHOP);
        return junkShops.stream()
                .map(user -> convertToDTO(user, 
                        reportService.getReportsByReporterId(user.getId()), 
                        reportService.getReportsByReportedUserId(user.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public void updateUserStatus(int userId, String status) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        user.setAccStatus(status);
        userRepository.save(user);
    }

    @Override
    public int getTotalUsers() {
        return (int) userRepository.count();
    }
    
    @Override
    public void addPenaltyPoints(int userId, int points) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        user.setPenaltyPoints(user.getPenaltyPoints() + points);
        userRepository.save(user);
    }
}
