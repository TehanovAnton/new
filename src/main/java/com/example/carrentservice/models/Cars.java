package com.example.carrentservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Cars {
    public Cars(String brandAuto, Integer carMileage) {
        this.brandAuto = brandAuto;
        this.carMileage = carMileage;
    }

    @Id
    private Integer id;

    @Getter
    @Setter
    private String brandAuto;

    @Getter
    @Setter
    private Integer carMileage;
}
