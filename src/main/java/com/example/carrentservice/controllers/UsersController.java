package com.example.carrentservice.controllers;

import com.example.carrentservice.models.User;
import com.example.carrentservice.repository.UserRepository;
import com.example.carrentservice.services.RegistrationServices;
import com.example.carrentservice.services.UserControllerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
@RequestMapping
public class UsersController
{
    @Autowired
    private UserRepository userRepository;

    private static RegistrationServices registrationServices;
    private static UserControllerServices usersControllerService;

    static
    {
        registrationServices = new RegistrationServices();
        usersControllerService = new UserControllerServices();
    }

    @GetMapping(value = { "/users/new" })
    public ModelAndView New(Model model)
    {
        usersControllerService.AddNewUserAttr(model);
        return usersControllerService.ReturnView("UsersNew");
    }

    @PostMapping(value = { "/users/create" })
    public ModelAndView Create(Model model, @ModelAttribute("user") User user)
    {
        registrationServices.Register(userRepository, user);
        return usersControllerService.ReturnView("UsersShow");
    }
}
