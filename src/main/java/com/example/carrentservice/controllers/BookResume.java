package com.example.carrentservice.controllers;

import com.example.carrentservice.models.BorrowedDate;
import com.example.carrentservice.models.Car;
import com.example.carrentservice.models.Customer;
import com.example.carrentservice.services.BorrowedDateService;
import com.example.carrentservice.services.CarService;
import com.example.carrentservice.services.CustomerService;
import com.example.carrentservice.services.MailService;
import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@RestController
@EnableEmailTools
@SessionAttributes({ "customer", "borrowedDate" })
@RequestMapping
public class BookResume {
    private CarService carService;
    private BorrowedDateService borrowedDateService;
    private CustomerService customerService;
    private MailService mailService;
    private Car carById;

    public BookResume(CarService carService, BorrowedDateService
            borrowedDateService, CustomerService customerService, MailService mailService)
    {
        this.carService = carService;
        this.borrowedDateService = borrowedDateService;
        this.customerService = customerService;
        this.mailService = mailService;
    }

    @GetMapping(value = "bookResume{car_id}")
    public ModelAndView showCustomerResume(Customer customer, BorrowedDate borrowedDate, Model model, @RequestParam(value = "car_id") Long carId) {
        carById = carService.findById(carId).get();
        model.addAttribute("carById", carById);
        model.addAttribute("customer", customer);
        model.addAttribute("borrowedDate", borrowedDate);
        return new ModelAndView("bookResume");
    }

    @PostMapping(value = "bookResume")
    public ModelAndView completeAll(Customer customer, BorrowedDate borrowedDate, SessionStatus status) {
        borrowedDate.setCustomer(customer);

        customerService.save(customer);
        borrowedDateService.save(borrowedDate);
        mailService.sendMail(customer, borrowedDate, carById);
        status.setComplete();
        return new ModelAndView("redirect:/");
    }
}
