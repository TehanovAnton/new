package com.example.carrentservice.services;

import com.example.carrentservice.models.Car;
import com.example.carrentservice.repository.CarDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private CarDAO carDAO;

    public CarServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public void save(Car car) {
        carDAO.save(car);
    }

    @Override
    public List<Car> newCars() {
        return carDAO.newCars();
    }

    @Override
    public List<Car> findAll() {
        return carDAO.findAll();
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carDAO.findById(id);
    }
}
