package com.example.carrentservice.models;

import com.example.carrentservice.validation.UniqueEmail;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = -5499172417961772372L;

    public Customer() {
        super();
    }

    public Customer(String email, String password, String fullName, String role, BigDecimal totalPrice, BigDecimal cardNumber, BigDecimal cvv, boolean isPaid, int borrowedCars, List<Car> cars) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.totalPrice = totalPrice;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.isPaid = isPaid;
        this.borrowedCars = borrowedCars;
        this.cars = cars;
    }

    @Id
    @Getter
    @Setter
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "fullName", length = 30)
    @NotEmpty(message = "fullName should not be empty")
    @Size(min = 2, max = 30, message = "fullName length must be between 2 and 30 characters")
    private String fullName;

    @Getter
    @Setter
    @Column(name = "email", length = 50)
    @NotEmpty(message = "email should not be empty")
    @Email(message = "email should be valid")
    @UniqueEmail
    private String email;

    @Getter
    @Setter
    @NotEmpty(message = "password should not be empty")
    @Size(min = 5, max = 16)
    @Column(name = "password", length = 16)
    private String password;

    @Size(min = 2, max = 50)
    @Column(name = "role", length = 50)
    @Getter
    @Setter
    private String role;

    @Getter
    @Setter
    @Column(name = "active")
    private boolean active;

    @Getter
    @Setter
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_role", joinColumns = @JoinColumn(name = "customer_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Getter
    @Setter
    @Column(name = "total_price")
    @Min(value = 0, message = "totalPrice should not be less then 0")
    private BigDecimal totalPrice = new BigDecimal(0);

    @Getter
    @Setter
    @Column(name = "card_number")
    @Digits(integer = 16, fraction = 0)
    private BigDecimal cardNumber;

    @Getter
    @Setter
    @Column(name = "cvv")
    @Digits(integer = 3, fraction = 0)
    private BigDecimal cvv;

    @Getter
    @Setter
    @Column(name = "is_paid")
    private boolean isPaid = false;

    @Getter
    @Setter
    @Column(name = "borrowed_cars")
    @Min(value = 0, message = "borrowed cars number should not be less than 0")
    private int borrowedCars;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Car> cars = new ArrayList<>();
}