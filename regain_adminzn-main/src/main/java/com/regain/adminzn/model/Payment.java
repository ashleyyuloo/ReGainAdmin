package com.regain.adminzn.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int id;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "amount_paid")
    private double amountPaid;

    @Column(name = "reference_number")
    private String referenceNumber;
}
