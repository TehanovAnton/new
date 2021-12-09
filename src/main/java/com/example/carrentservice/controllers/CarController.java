package com.example.carrentservice.controllers;

import com.example.carrentservice.models.Car;
import com.example.carrentservice.models.CarJson;
import com.example.carrentservice.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class CarController {

    @Autowired
    private CarService carService;

    private ObjectWriter ow;

    public CarController() {
        ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    @GetMapping(value = "/cars")
    public List<CarJson> allCars() {
        List<Car> cars = carService.findAll();
        List<CarJson> carJsons = new ArrayList<>();

        for (Integer i = 0; i < cars.size(); i++)
            carJsons.add(cars.get(i).carJsonObj());

        return carJsons;
    }

    @GetMapping(value = "/car{id}")
    public CarJson carById(@RequestParam("id") Long carId) {
        Optional<Car> optCar = carService.findById(carId);
        return optCar.isEmpty() ? null : optCar.get().carJsonObj();
    }

    @PostMapping("/car/new")
    public Car createCar(@RequestParam("name") String name, @RequestParam("description") String description,
                         @RequestParam("price") BigDecimal price) {
        Car car = new Car(name, description, price);
        carService.save(car);
        return car;
    }

    @PutMapping("/car/update/{id}")
    public Car updateCar(@RequestParam("id") Long carId,
                         @RequestParam(value = "name", required = false) String name,
                         @RequestParam(value = "description", required = false) String description,
                         @RequestParam(value = "price", required = false) BigDecimal price) {
        Car car = carService.findById(carId).get();

        if (name != null) car.setName(name);
        if (description != null) car.setDescription(description);
        if (price != null) car.setPrice(price);
        carService.save(car);

        return car;
    }

    @DeleteMapping("car/delete/{id}")
    public Car deleteCar(@RequestParam("id") Long carId) {
        Car car = carService.findById(carId).get();
        return carService.deleteById(carId);
    }
}
