package com.example.carrentservice.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class CarJson {
    public CarJson(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private BigDecimal price;
}
