package com.example.carrentservice.validation;

import com.example.carrentservice.models.Cars;

public class CarValidator {

    public static Boolean Validate(Cars car) {
        return CheckCarMileage(car.getCarMileage());
    }

    private static Boolean  CheckCarMileage(Integer carMileage) {
        return carMileage >= 0;
    }
}
