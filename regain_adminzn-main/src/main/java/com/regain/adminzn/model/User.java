package com.regain.adminzn.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "role_type", referencedColumnName = "role_id")  
    private Role role;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_number")
    private String phone;

    @Column(name = "penalty_points")
    private int penaltyPoints;

    @Column(name = "commission_balance")
    private double commissionBalance;

    @Column(name = "profile_picture")
    private byte[] profileImagePath;

    @Column(name = "js_name")
    private String junkshopName;

    @Column(name = "acc_status")
    private String accStatus;
    
}
