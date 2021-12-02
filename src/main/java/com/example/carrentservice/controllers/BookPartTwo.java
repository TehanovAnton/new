package com.example.carrentservice.controllers;

import com.example.carrentservice.models.BorrowedDate;
import com.example.carrentservice.models.Car;
import com.example.carrentservice.models.Customer;
import com.example.carrentservice.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@SessionAttributes({ "customer", "borrowedDate" })
@RequestMapping
public class BookPartTwo {

    private CarService carService;

    public BookPartTwo(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "bookPartTwo{car_id}")
    public ModelAndView showSessionCar(Customer customer, BorrowedDate borrowedDate, Model model, @RequestParam(value = "car_id") Long carId) {
        Car carById = carService.findById(carId).get();

        model.addAttribute("carById", carById);
        model.addAttribute("customer", customer);
        model.addAttribute("borrowedDate", borrowedDate);

        return new ModelAndView("bookPartTwo");
    }

    @PostMapping(value = "bookPartTwo")
    public ModelAndView completeCustomer(Customer customer, Model model,
                                         @RequestParam(value = "car_id") Long carId,
                                         @RequestParam(value = "fullName") String fullName,
                                         @RequestParam(value = "login") String email,
                                         @RequestParam(value = "password") String password) {
        model.addAttribute("car_id", carId);
        initCustomer(customer, fullName, email, password);

        String url = "redirect:/bookPartThree?car_id=" + carId.toString();
        return new ModelAndView(url);
    }

    private void initCustomer(Customer customer, String fullName, String email, String password) {
        customer.setFullName(fullName);
        customer.setLogin(email);
        customer.setPassword(password);
    }
}
