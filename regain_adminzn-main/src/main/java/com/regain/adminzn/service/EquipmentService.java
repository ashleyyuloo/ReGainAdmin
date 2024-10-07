package com.regain.adminzn.service;

import com.regain.adminzn.dto.EquipmentDTO;
import java.util.List;

public interface EquipmentService {
    List<EquipmentDTO> getAllEquipment();
    List<EquipmentDTO> getAvailableEquipment();
    List<EquipmentDTO> getBookedEquipment();
    List<EquipmentDTO> getEquipmentByRenterId(int renterId);
    EquipmentDTO getEquipmentById(int id);
    void deleteEquipmentById(int id);
}
