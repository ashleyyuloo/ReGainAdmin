package com.regain.adminzn.dto;

import java.util.List;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EquipmentDTO {
    private int id;
    private String name;
    private double price;
    private String imagePath;
    private AddressDTO location;
    private UserDTO renter;
    private UserDTO rentee;
    private String status;
    private List<BookingDTO> bookings;
    private List<ReportDTO> reportedReports;
}

