package com.example.carrentservice.repository;

import com.example.carrentservice.models.Cars;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Cars, Long> {
}
