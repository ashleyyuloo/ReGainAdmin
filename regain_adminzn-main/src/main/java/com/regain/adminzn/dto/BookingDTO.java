package com.regain.adminzn.dto;

import java.util.Date;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private int id;
    private String equipmentName;
    private String renter;
    private String rentee;
    private double price;
    private double totalPrice;
    private Date startDate;
    private Date endDate;
    private int equipmentId;
}
