package com.example.carrentservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Cars")
@NoArgsConstructor
public class Car {

    public Car(String brand, Integer rentPrice) {
        this.brand = brand;
        this.rentPrice = rentPrice;
        this.rentState = "free";
    }

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String brand;

    @Getter
    @Setter
    @Column(nullable = false)
    // rent price per day ($)
    private Integer rentPrice;

    @Getter
    @Setter
    @Column(columnDefinition = "nvarchar(255) not null default 'free'")
    private String rentState;


    private Integer rent_id;

    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rent_id", nullable = false, insertable = false, updatable = false)
    private Rent rent;
}
