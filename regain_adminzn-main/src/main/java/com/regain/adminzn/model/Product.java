package com.regain.adminzn.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "user_id", nullable = false)
    private User seller;

    @Column(name = "descript")
    private String description;

    @Column(name = "weight", length = 10, precision = 2)
    private Double weight;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private double soldPrice;

    @ManyToOne
    @JoinColumn(name = "location", referencedColumnName = "address_id")
    private Address location;

    @Column(name = "image")
    private String imagePath;

}
