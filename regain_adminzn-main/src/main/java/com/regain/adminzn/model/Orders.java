package com.regain.adminzn.model;

import lombok.*;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "user_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "payment_method", referencedColumnName = "payment_id")
    private Payment paymentMethod;

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "current_status")
    private String status; 

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "delivery_method")
    private String deliveryMethod;

    @Column(name = "delivery_date")
    private Date deliveryDate;
}
