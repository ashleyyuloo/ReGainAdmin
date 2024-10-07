package com.regain.adminzn.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipments")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "renter_id", referencedColumnName = "user_id")
    private User renter;

    @Column(name = "equipment_name")
    private String name;

    @Column(name = "descript")
    private String description;

    @ManyToOne
    @JoinColumn(name = "location", referencedColumnName = "address_id")
    private Address location;
    
    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category_id")
    private Category category;

    @Column(name = "price")
    private double price;

    @OneToMany(mappedBy = "equipment", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    @Column(name = "image")
    private String imagePath;

}
