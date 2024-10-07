package com.regain.adminzn.model;

import java.time.LocalDateTime;
import java.sql.Date;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "orderlog")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracking_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Orders order;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Column(name = "previous_status")
    private String previousStatus;

    @ManyToOne
    @JoinColumn(name = "updated_by_user", referencedColumnName = "user_id")
    private User updatedByUser;

    @Column(name = "time_stamp")
    private LocalDateTime timestamp;
}
