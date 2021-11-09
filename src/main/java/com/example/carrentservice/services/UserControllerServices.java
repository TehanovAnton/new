package com.example.carrentservice.services;

import com.example.carrentservice.models.User;
import lombok.NoArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@NoArgsConstructor
public class UserControllerServices {
    public ModelAndView ReturnView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        return modelAndView;
    }

    public void AddNewUserAttr(Model model) {
        User user = new User();
        model.addAttribute("user", user);
    }
}
