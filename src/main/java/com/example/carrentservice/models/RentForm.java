package com.example.carrentservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
public class RentForm {
    @Getter
    @Setter
    public Integer leaseDuration;

    @Getter
    @Setter
    public String rentDate;

    @Getter
    @Setter
    public String brand;
}
