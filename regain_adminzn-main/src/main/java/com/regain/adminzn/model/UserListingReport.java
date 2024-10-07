package com.regain.adminzn.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "userreports")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserListingReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_report_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "reporter", referencedColumnName = "user_id")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "reported_user", referencedColumnName = "user_id")
    private User reportedUser;

    @ManyToOne
    @JoinColumn(name = "reason_category", referencedColumnName = "report_category_id")
    private ReportCategory category;

    @Column(name = "report_reply")
    private String reportReply;

    @Column(name = "details")
    private String details;

    @Column(name = "time_stamp")
    private LocalDateTime timestamp;

    @Column(name = "user_report_status")
    private String status;
}
