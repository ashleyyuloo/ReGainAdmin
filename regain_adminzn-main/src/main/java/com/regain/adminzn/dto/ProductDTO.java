package com.regain.adminzn.dto;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    private int id;
    private String productName;
    private String categoryName;
    private UserDTO seller; 
    private double soldPrice;
    private String listingStatus;
    private String imagePath;
    private String description;
    private AddressDTO location;

    private List<ReportDTO> productReported;
}
