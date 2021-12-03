package com.example.carrentservice.controllers;

import com.example.carrentservice.models.Customer;
import com.example.carrentservice.models.Role;
import com.example.carrentservice.services.CustomerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView registration(Customer customer, Model model) {
        Customer customerFromDb = customerService.findCustomerByFullName(customer.getFullName());

        if (customerFromDb != null) {
            return new ModelAndView("redirect:/registration");
        }

        customer.setActive(true);
        customer.setRoles(Collections.singleton(Role.USER));
        customerService.save(customer);

        return new ModelAndView("redirect:/");
    }
}
