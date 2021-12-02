package com.example.carrentservice.controllers;

import com.example.carrentservice.models.Customer;
import lombok.Getter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
public class Authorization {

    @GetMapping(value = "/login")
    public ModelAndView loginPage(Model model) {
        model.addAttribute("customer", new Customer());
        return new ModelAndView();
    }

    @PostMapping(value = "/login")
    public ModelAndView login(Customer customer, Model model) {

        model.addAttribute("customer", new Customer());
        return new ModelAndView();
    }
}
