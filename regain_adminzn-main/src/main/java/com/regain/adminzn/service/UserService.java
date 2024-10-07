package com.regain.adminzn.service;

import com.regain.adminzn.dto.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    List<UserDTO> getHouseholds();
    List<UserDTO> getJunkShops();
    UserDTO getUserById(int userId);
    void updateUserStatus(int userId, String status);
    int getTotalUsers();
    void addPenaltyPoints(int userId, int points);
}