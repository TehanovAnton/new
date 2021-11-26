package com.example.carrentservice.repository;

import com.example.carrentservice.models.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAllByBrand(String brand);
}
