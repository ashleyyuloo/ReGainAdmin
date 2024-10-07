package com.regain.adminzn.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "reportcategories")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReportCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_category_id")
    private int reportCategoryId;

    private String category;
}
