package com.example.carrentservice.controllers;

import com.example.carrentservice.models.SignInSet;
import com.example.carrentservice.models.User;
import com.example.carrentservice.repository.UserRepository;
import com.example.carrentservice.services.SessionControllerServices;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class SessionsController {

    @Getter
    private static User currentUser;

    @Autowired
    private UserRepository userRepository;

    private static SessionControllerServices services;

    static {
        services = new SessionControllerServices();
    }

    @GetMapping("/user/sign_in")
    public ModelAndView SignIn(Model model) {
        services.AddViewAttr(model, "signInSet", new SignInSet());
        return services.ReturnView("SignIn");
    }

    @PostMapping("/user/session/create")
    public void CreateSession(@ModelAttribute("signInSet") SignInSet signInSet, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (services.CheckUserBySignInSet(signInSet, userRepository)) {
            services.CreateSession(request);
            currentUser = services.getUser(signInSet, userRepository);
            response.sendRedirect("/rent/new");
        }
        else {
            response.sendRedirect("/user/sign_in");
        }
    }

    @PostMapping("/user/sign_out")
    public ModelAndView SignOut(Model model, HttpServletRequest request) {
        services.DestroySession(request);
        services.AddViewAttr(model, "signInSet", new SignInSet());
        return services.ReturnView("SignIn");
    }
}
