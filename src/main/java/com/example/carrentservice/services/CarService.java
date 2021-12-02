package com.example.carrentservice.services;

import com.example.carrentservice.models.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();

    List<Car> newCars();

    Optional<Car> findById(Long id);

    void save(Car car);
}
