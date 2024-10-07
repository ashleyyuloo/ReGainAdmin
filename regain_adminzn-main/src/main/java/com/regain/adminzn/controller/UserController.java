package com.regain.adminzn.controller;

import com.regain.adminzn.dto.AddressDTO;
import com.regain.adminzn.dto.EquipmentDTO;
import com.regain.adminzn.dto.ProductDTO;
import com.regain.adminzn.dto.ReportDTO;
import com.regain.adminzn.dto.UserDTO;
import com.regain.adminzn.service.AddressService;
import com.regain.adminzn.service.EquipmentService;
import com.regain.adminzn.service.ProductService;
import com.regain.adminzn.service.ReportService;
import com.regain.adminzn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private final EquipmentService equipmentService;  
    private final AddressService addressService;

    @Autowired
    private ReportService reportService;

    @Autowired
    public UserController(UserService userService, ProductService productService, EquipmentService equipmentService, AddressService addressService) {
        this.userService = userService;
        this.productService = productService;
        this.equipmentService = equipmentService;  
        this.addressService = addressService;
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        List<UserDTO> households = userService.getHouseholds();
        List<UserDTO> junkshops = userService.getJunkShops();
        model.addAttribute("users", users);
        model.addAttribute("households", households);
        model.addAttribute("junkshops", junkshops);
        return "users/index";
    }

    @GetMapping("/users/{id}")
    public String getUserDetails(@PathVariable("id") int id, Model model) {
        UserDTO user = userService.getUserById(id);
        List<AddressDTO> addresses = addressService.getAddressesByUserId(id); 
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        user.setAddresses(addresses);
        List<ProductDTO> products = productService.getProductsBySellerId(id);
         List<EquipmentDTO> equipment = equipmentService.getEquipmentByRenterId(id);
         List<ReportDTO> reportedReports = reportService.getReportsByReportedUserId(id);
        model.addAttribute("reportedReports", reportedReports);
        model.addAttribute("user", user);
        model.addAttribute("products", products);
        model.addAttribute("equipment", equipment);
        return "users/selected-user";
    }

    @PostMapping("/users/{id}/restrict")
    public String restrictUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUserStatus(id, "Restricted");
            redirectAttributes.addFlashAttribute("successMessage", "User restricted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error restricting user");
        }
        return "redirect:/users";
    }

    @PostMapping("/users/{id}/freeze")
    public String freezeUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUserStatus(id, "Frozen");
            redirectAttributes.addFlashAttribute("successMessage", "User frozen successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error freezing user");
        }
        return "redirect:/users";
    }

    @PostMapping("/users/{id}/ban")
    public String banUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUserStatus(id, "Banned");
            redirectAttributes.addFlashAttribute("successMessage", "User banned successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error banning user");
        }
        return "redirect:/users";
    }
}
