package com.example.carrentservice.controllers;

import com.example.carrentservice.models.SignInSet;
import com.example.carrentservice.repository.UserRepository;
import com.example.carrentservice.services.SessionControllerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class SessionsController {

    @Autowired
    private UserRepository userRepository;

    private static SessionControllerServices sessionControllerServices;

    static {
        sessionControllerServices = new SessionControllerServices();
    }

    @GetMapping("/user/sign_in")
    public ModelAndView SignIn(Model model) {
        sessionControllerServices.AddViewAttr(model, "signInSet", new SignInSet());
        return sessionControllerServices.ReturnView("SignIn");
    }

    @PostMapping("/user/session/create")
    public ModelAndView CreateSession(@ModelAttribute("signInSet") SignInSet signInSet, HttpServletRequest request) {
        ModelAndView modelAndView;
        if (sessionControllerServices.CheckUserBySignInSet(signInSet, userRepository)) {
            sessionControllerServices.CreateSession(request);
            modelAndView = sessionControllerServices.ReturnView("Home");
        }
        else
            modelAndView = sessionControllerServices.ReturnView("SignIn");

        return modelAndView;
    }

    @PostMapping("/user/sign_out")
    public ModelAndView SignOut(Model model, HttpServletRequest request) {
        sessionControllerServices.DestroySession(request);
        sessionControllerServices.AddViewAttr(model, "signInSet", new SignInSet());
        return sessionControllerServices.ReturnView("SignIn");
    }
}
