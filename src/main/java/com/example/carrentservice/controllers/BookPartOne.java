package com.example.carrentservice.controllers;

import com.example.carrentservice.models.AvailableCarsResult;
import com.example.carrentservice.models.BorrowedDate;
import com.example.carrentservice.models.Car;
import com.example.carrentservice.models.Customer;
import com.example.carrentservice.services.BorrowedDateService;
import com.example.carrentservice.services.CarService;
import com.example.carrentservice.services.CustomerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Calendar;
import java.util.List;

@RestController
@SessionAttributes({ "customer", "borrowedDate" })
@RequestMapping
public class BookPartOne {

    private CarService carService;
    private BorrowedDateService borrowedDateService;
    private CustomerService customerService;

    public BookPartOne(CarService carService, BorrowedDateService borrowedDateService, CustomerService customerService) {
        this.carService = carService;
        this.borrowedDateService = borrowedDateService;
        this.customerService = customerService;
    }

    @GetMapping(value = "/bookPartOne{car_id}")
    public ModelAndView checkDates(Model model,
                                   @RequestParam(value = "car_id") Long CarId,
                                   @RequestParam(value = "start_date", defaultValue = "1800-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar startDate,
                                   @RequestParam(value = "end_date", defaultValue = "3000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar endDate) {

        Car carById = carService.findById(CarId).get();
        List<AvailableCarsResult> availableCarById = borrowedDateService.checkAvailableCarById(startDate, endDate, CarId);
        model.addAttribute("carById", carById);
        model.addAttribute("availableCarById", availableCarById);
        return new ModelAndView("bookPartOne");
    }

    @PostMapping(value = "/bookPartOne")
    public ModelAndView createNewCustomer(Model model, @RequestParam(value = "car_id") Long CarId,
                                    @RequestParam(value = "start_date", defaultValue = "1800-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar startDate,
                                    @RequestParam(value = "end_date", defaultValue = "3000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar endDate) {
        Car car = carService.findById(CarId).get();
        Customer customer = new Customer();
        initCustomer(customer, car);

        BorrowedDate borrowedDate = new BorrowedDate();
        initBorrowedDate(borrowedDate, car, customer, startDate, endDate);

        model.addAttribute("car_id", CarId);
        model.addAttribute("customer", customer);
        model.addAttribute("borrowedDate", borrowedDate);

        String url = "redirect:/bookPartTwo?car_id=" + CarId.toString();
        return new ModelAndView(url);
    }

    private void initCustomer(Customer customer, Car car) {
        customer.getCars().add(car);
        customer.setBorrowedCars(customer.getBorrowedCars() + 1);
        customer.setTotalPrice(customer.getTotalPrice().add(car.getPrice()));
    }

    private void initBorrowedDate(BorrowedDate borrowedDate, Car car, Customer customer, Calendar startDate, Calendar endDate) {
        borrowedDate.setCar(car);
        borrowedDate.setCustomer(customer);
        borrowedDate.setStartDate(startDate);
        borrowedDate.setEndDate(endDate);
    }
}
