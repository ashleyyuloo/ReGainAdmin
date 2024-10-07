package com.regain.adminzn.dto;

import java.util.List;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;
    private String role;
    private String lastName;
    private String firstName;
    private String userName;
    private String email;
    private String phone;
    private int penaltyPoints;
    private double commissionBalance;
    private byte[] profileImagePath;
    private double rating;
    private String junkshopName;

    private List<ReportDTO> reportedReports;
    private List<AddressDTO> addresses;
    private List<ProductDTO> products;
    private List<EquipmentDTO> equipment;

    public UserDTO(String userName) {
        this.userName = userName;
    }
}
