package com.example.carrentservice.controllers;

import com.example.carrentservice.models.Rents;
import com.example.carrentservice.repository.RentRepository;
import com.example.carrentservice.services.RentsControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

public class RentsController {
    @Autowired
    private RentRepository rentsRepository;

    private static RentsControllerServices rentsControllerServices;

    static
    {
        rentsControllerServices = new RentsControllerServices();
    }

    @GetMapping(value = { "/users/new" })
    public ModelAndView New(Model model)
    {
        rentsControllerServices.AddNewRentAttr(model);
        return rentsControllerServices.ReturnView("RentsNew");
    }

    @PostMapping(value = { "/users/create" })
    public ModelAndView Create(Model model, @ModelAttribute("rent") Rents rent)
    {
        rentsControllerServices.SaveRent(rentsRepository, rent);
        return rentsControllerServices.ReturnView("UsersShow");
    }
}
