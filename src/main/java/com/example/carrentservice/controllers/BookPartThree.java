package com.example.carrentservice.controllers;

import com.example.carrentservice.models.BorrowedDate;
import com.example.carrentservice.models.Car;
import com.example.carrentservice.models.Customer;
import com.example.carrentservice.services.BorrowedDateService;
import com.example.carrentservice.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@RestController
@SessionAttributes({ "customer", "borrowedDate" })
public class BookPartThree {

    private CarService carService;
    private BorrowedDateService borrowedDateService;

    public BookPartThree(CarService carService, BorrowedDateService borrowedDateService) {
        this.carService = carService;
        this.borrowedDateService = borrowedDateService;
    }

    @GetMapping(value = "bookPartThree{car_id}")
    public ModelAndView showSessionCar(Customer customer, Model model, @RequestParam(value = "car_id") Long carId) {
        Car carById = carService.findById(carId).get();
        model.addAttribute("carById", carById);
        model.addAttribute("customer", customer);
        return new ModelAndView("bookPartThree");
    }

    @PostMapping(value = "bookPartThree")
    public ModelAndView realizePayment(Customer customer,
                                       Model model,
                                       @RequestParam(value = "car_id") Long carId,
                                       @RequestParam(value = "cardNumber") String cardNumber,
                                       @RequestParam(value = "cvv") String cvv) {

        long days = borrowedDateService.countDays((BorrowedDate) model.getAttribute("borrowedDate"));
        initCustomer(customer, new BigDecimal(cardNumber), new BigDecimal(cvv), days);

        model.addAttribute("car_id", carId);
        String url = "redirect:/bookResume?car_id=" + carId;
        return new ModelAndView(url);
    }

    private void initCustomer(Customer customer, BigDecimal cardNumber, BigDecimal cvv, long days) {
        customer.setTotalPrice(customer.getTotalPrice().multiply(new BigDecimal(days)));
        customer.setRole("ROLE_USER");
        customer.setPaid(true);
        customer.setCardNumber(cardNumber);
        customer.setCvv(cvv);
    }
}
