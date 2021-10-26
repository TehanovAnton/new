package com.example.carrentservice.services;

import com.example.carrentservice.models.Rents;
import com.example.carrentservice.models.Users;
import com.example.carrentservice.repository.RentRepository;
import lombok.NoArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@NoArgsConstructor
public class SessionControllerServices {
    public ModelAndView ReturnView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        return modelAndView;
    }

    public Boolean CheckUser(Users user) {
        return true;
    }

    public void AddNewUserAttr(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
    }
}
