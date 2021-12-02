package com.example.carrentservice.controllers;

import com.example.carrentservice.models.Car;
import com.example.carrentservice.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.MalformedParameterizedTypeException;

@RestController
@RequestMapping
public class CarDetail {

    private CarService carService;

    public CarDetail(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/carDetail{car_id}")
    public ModelAndView carDetail(Model model, @RequestParam(value = "car_id") Long carId) {
        ModelAndView modelAndView = new ModelAndView("carDetail");
        Car carById = carService.findById(carId).get();
        model.addAttribute("carById", carById);
        return modelAndView;
    }
}