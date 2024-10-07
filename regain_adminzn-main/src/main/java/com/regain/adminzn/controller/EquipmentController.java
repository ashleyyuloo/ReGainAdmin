package com.regain.adminzn.controller;

import com.regain.adminzn.dto.EquipmentDTO;
import com.regain.adminzn.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/equipments")
    public String viewEquipments(Model model) {
        List<EquipmentDTO> equipments = equipmentService.getAllEquipment();
        List<EquipmentDTO> availableEquipments = equipmentService.getAvailableEquipment();
        List<EquipmentDTO> bookedEquipments = equipmentService.getBookedEquipment();
        model.addAttribute("equipments", equipments);
        model.addAttribute("availableEquipments", availableEquipments);
        model.addAttribute("bookedEquipments", bookedEquipments);
        return "equipment/index";
    }

    @GetMapping("/equipments/details/{id}")
    public String viewEquipmentDetails(@PathVariable("id") int id, Model model) {
        EquipmentDTO equipment = equipmentService.getEquipmentById(id);
        model.addAttribute("equipment", equipment);
        return "equipment/selected-equipment";
    }

    @PostMapping("/equipment/delete/{id}")
    public String deleteEquipment(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            equipmentService.deleteEquipmentById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Equipment deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting equipment");
        }
        
        return "redirect:/equipments";
    }
}
