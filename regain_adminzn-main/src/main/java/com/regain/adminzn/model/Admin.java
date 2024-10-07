package com.regain.adminzn.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adminuser")
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="pass")
    private String password;

    @Column(name="email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "admin_role", referencedColumnName = "role_id") 
    private Role role;
}
