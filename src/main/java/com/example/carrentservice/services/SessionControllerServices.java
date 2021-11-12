package com.example.carrentservice.services;

import com.example.carrentservice.models.Rents;
import com.example.carrentservice.models.SignInSet;
import com.example.carrentservice.models.User;
import com.example.carrentservice.repository.RentRepository;
import com.example.carrentservice.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@NoArgsConstructor
public class SessionControllerServices {

    public ModelAndView ReturnView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        return modelAndView;
    }

    public Boolean CheckUser(User user) {
        return true;
    }

    public void AddViewAttr(Model model, String attrName, Object attr) {
        model.addAttribute(attrName, attr);
    }

    public void CreateSession(HttpServletRequest request) {
            request.getSession().setAttribute("SESSION_ON", true);
    }

    public void DestroySession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public boolean CheckUserBySignInSet(SignInSet signInSet, UserRepository userRepository) {
        User user = userRepository.findByEmailAndPassword(signInSet.getEmail(), signInSet.getPassword());
        return user != null;
    }
}
