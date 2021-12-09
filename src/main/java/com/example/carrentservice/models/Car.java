package com.example.carrentservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
public class Car implements Serializable {
    private static final long serialVersionUID = 7034352443015914334L;

    public Car() {
        super();
    }

    public Car(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name", length = 100)
    @Getter
    @Setter
    private String name;

    @Size(min = 20, max = 300)
    @Column(name = "description", length = 300)
    @Getter
    @Setter
    private String description;

    @Digits(integer = 5, fraction = 2)
    @Column(name = "price")
    @Getter
    @Setter
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cars")
    @Getter
    @Setter
    private List<Customer> customers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    @Getter
    @Setter
    private List<BorrowedDate> borrowedDates = new ArrayList<>();

    public CarJson carJsonObj() {
        return new CarJson(name, description, price);
    }
}
