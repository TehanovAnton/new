package com.example.carrentservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = -5499172417961772372L;

    public Customer() {
        super();
    }

    public Customer(String login, String password, String fullName, String role, BigDecimal totalPrice, BigDecimal cardNumber, BigDecimal cvv, boolean isPaid, int borrowedCars, List<Car> cars) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.totalPrice = totalPrice;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.isPaid = isPaid;
        this.borrowedCars = borrowedCars;
        this.cars = cars;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    @Getter
    @Setter
    private Long id;

    @Size(min = 2, max = 50)
    @Column(name = "login", length = 50)
    @Getter
    @Setter
    private String login;

    @Size(min = 5, max = 60)
    @Column(name = "password", length = 60)
    @Getter
    @Setter
    private String password;

    @Size(min = 2, max = 50)
    @Column(name = "fullName", length = 50)
    @Getter
    @Setter
    private String fullName;

    @Size(min = 5, max = 50)
    @Column(name = "role", length = 50)
    @Getter
    @Setter
    private String role;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "total_price")
    @Getter
    @Setter
    private BigDecimal totalPrice = new BigDecimal(0);

    @Digits(integer = 16, fraction = 0)
    @Column(name = "card_number")
    @Getter
    @Setter
    private BigDecimal cardNumber;

    @Digits(integer = 3, fraction = 0)
    @Column(name = "cvv")
    @Getter
    @Setter
    private BigDecimal cvv;

    @Column(name = "is_paid")
    @Getter
    @Setter
    private boolean isPaid = false;

    @Column(name = "borrowed_cars")
    @Getter
    @Setter
    private int borrowedCars;

    @ManyToMany(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Car> cars = new ArrayList<>();
}