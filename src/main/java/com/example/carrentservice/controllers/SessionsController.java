package com.example.carrentservice.controllers;

import com.example.carrentservice.models.Rents;
import com.example.carrentservice.models.Users;
import com.example.carrentservice.services.RentsControllerServices;
import com.example.carrentservice.services.SessionControllerServices;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

public class SessionsController {
    private static SessionControllerServices sessionControllerServices;

    static
    {
        sessionControllerServices = new SessionControllerServices();
    }

    @GetMapping(value = { "/users/new" })
    public ModelAndView New(Model model)
    {
        sessionControllerServices.AddNewUserAttr(model);
        return sessionControllerServices.ReturnView("SignIn");
    }

    @PostMapping(value = { "/users/create" })
    public ModelAndView Create(Model model, @ModelAttribute("user") Users user)
    {
        sessionControllerServices.CheckUser(user);
        return sessionControllerServices.ReturnView("Home");
    }
}
