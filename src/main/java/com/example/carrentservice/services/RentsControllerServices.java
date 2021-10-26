package com.example.carrentservice.services;

import com.example.carrentservice.models.Rents;
import com.example.carrentservice.repository.RentRepository;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public class RentsControllerServices {
    public ModelAndView ReturnView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        return modelAndView;
    }

    public void SaveRent(RentRepository rentsRepository, Rents rent) {
        rentsRepository.save(rent);
    }

    public void AddNewRentAttr(Model model) {
        Rents rent = new Rents();
        model.addAttribute("rent", rent);
    }
}
