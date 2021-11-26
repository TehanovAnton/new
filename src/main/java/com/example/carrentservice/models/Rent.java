package com.example.carrentservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Rents")
@NoArgsConstructor
@AllArgsConstructor
public class Rent {
    public Rent(User user, Car car, Date rentDate, Integer leaseDuration) {
        this.user = user;
        this.user_id = user.getId();

        this.car = car;
        this.car_id = car.getId();

        this.rentDate = rentDate;
        this.leaseDuration = leaseDuration;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false)
    private Date rentDate;

    @Getter
    @Setter
    @Column(nullable = false)
    private Integer leaseDuration;

    @Column(nullable = false)
    private Integer user_id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @Column(nullable = false)
    private Integer car_id;

    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", insertable = false, updatable = false)
    private Car car;
}
