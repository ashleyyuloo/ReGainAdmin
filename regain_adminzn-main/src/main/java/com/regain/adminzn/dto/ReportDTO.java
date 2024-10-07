package com.regain.adminzn.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDTO {
    private int reportId;
    private String reporter;
    private String reported;
    private String category;
    private LocalDateTime timestamp;
    private String status;
    private String reportType;
    private int reportedUserId; 
    private int reportedEquipmentId; 
    private int reportedProductId;
    private String details;
    private String reportReply;
}
