package com.example.carrentservice.controllers;

import com.example.carrentservice.models.Customer;
import com.example.carrentservice.models.Role;
import com.example.carrentservice.services.CustomerService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.*;
import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping
public class RegistrationController {
    private CustomerService customerService;

    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registrationPage(Model model) {
        model.addAttribute("customer", new Customer());
        return new ModelAndView("Registration");
    }

    @PostMapping(value = "/registration")
    public ModelAndView registration(Model model, @ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("Registration");
        }

        customer.setRoles(Collections.singleton(Role.USER));
        customerService.save(customer);

        return new ModelAndView("redirect:/");
    }
}
