package com.example.carrentservice.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

public class AvailableCarsResult implements Serializable {
    private static final long serialVersionUID = 3209915747110932732L;

    public AvailableCarsResult(Long id, Long carId, String carName, String carDescription, BigDecimal carPrice) {
        this.id = id;
        this.carId = carId;
        this.carName = carName;
        this.carDescription = carDescription;
        this.carPrice = carPrice;
    }

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Long carId;

    @Getter
    @Setter
    private String carName;

    @Getter
    @Setter
    private String carDescription;

    @Getter
    @Setter
    private BigDecimal carPrice;
}
