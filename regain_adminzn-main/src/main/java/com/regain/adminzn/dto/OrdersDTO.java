package com.regain.adminzn.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private int id;
    private String productName;
    private String seller;
    private UserDTO buyer;
    private double sellingPrice;
    private double boughtPrice;
    private String status; 
    private String paymentType;
    private String referenceNumber;
    private String deliveryMethod;
    private LocalDateTime orderDate;
    private LocalDateTime paidDate;
    private LocalDateTime receivedDate;
    private int productId; 
}
