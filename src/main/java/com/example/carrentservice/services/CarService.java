package com.example.carrentservice.services;

import com.example.carrentservice.models.AvailableCarsResult;
import com.example.carrentservice.models.Car;
import com.example.carrentservice.repository.CarDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }
    
    public void save(Car car) {
        carDAO.save(car);
    }
    
    public List<Car> newCars() {
        return carDAO.newCars();
    }
    
    public List<Car> findAll() {
        return carDAO.findAll();
    }

    public List<Car> findAllByCustomerId(Long id) {
        List<Long> cars_id = carDAO.findAllByCustomerId(id);
        List<Car> cars = new ArrayList<>();
        for(int i = 0; i < cars_id.size(); i++) {
            Long car_id = cars_id.get(i);
            Car car = carDAO.findById(car_id).get();
            cars.add(car);
        }

        return cars;
    }

    public Car deleteById(Long id) {
        Car car = carDAO.findById(id).get();
        carDAO.deleteById(id);
        return car;
    }

    public List<Long> carsId(List<AvailableCarsResult> availableCars) {
        List<Long> cars_id = new ArrayList<>();
        for (int i = 0; i < availableCars.size(); i++) {
            cars_id.add(availableCars.get(i).getId());
        }

        return cars_id;
    }
    
    public Optional<Car> findById(Long id) {
        return carDAO.findById(id);
    }
}
