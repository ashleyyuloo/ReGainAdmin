package com.regain.adminzn.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eqlistingreports")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EQListingReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "reporter", referencedColumnName = "user_id")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "reported_eqlisting", referencedColumnName = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "reason_category", referencedColumnName = "report_category_id")
    private ReportCategory category;

    @Column(name = "report_reply")
    private String reportReply;

    @Column(name = "details")
    private String details;

    @Column(name = "time_stamp")
    private LocalDateTime timestamp;

    @Column(name = "report_status")
    private String status;

}
