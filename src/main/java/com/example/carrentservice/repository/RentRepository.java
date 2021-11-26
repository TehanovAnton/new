package com.example.carrentservice.repository;

import com.example.carrentservice.models.Rent;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<Rent, Long> {
}
