package com.regain.adminzn.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressID;

    @lombok.NonNull
    @Column(name = "unit_number")
    private String unitNumber;

    @lombok.NonNull
    @Column(name = "street")
    private String street;

    @lombok.NonNull
    @Column(name = "barangay")
    private String barangay;

    @lombok.NonNull
    @Column(name = "city")
    private String city;

    @lombok.NonNull
    @Column(name = "province")
    private String province;

    @lombok.NonNull
    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    // @OneToMany(mappedBy = "address", fetch=FetchType.EAGER)
    // private Collection<Orders> order;
}