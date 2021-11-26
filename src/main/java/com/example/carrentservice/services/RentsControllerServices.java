package com.example.carrentservice.services;

import com.example.carrentservice.controllers.SessionsController;
import com.example.carrentservice.models.Car;
import com.example.carrentservice.models.Rent;
import com.example.carrentservice.models.RentForm;
import com.example.carrentservice.repository.CarRepository;
import com.example.carrentservice.repository.RentRepository;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RentsControllerServices {
    public Rent rentByForm(RentForm rentForm, CarRepository repository) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date rentDate = format.parse(rentForm.getRentDate());
        List<Car> cars = repository.findAllByBrand(rentForm.brand);

        Rent rent = new Rent(SessionsController.getCurrentUser(), cars.get(0), rentDate, rentForm.leaseDuration);
        return rent;
    }

    public void AddNewRentFormAttr(Model model) {
        RentForm rentForm = new RentForm();
        model.addAttribute("rentForm", rentForm);
    }

    public void SaveRent(RentRepository rentsRepository, Rent rent) {
        rentsRepository.save(rent);
    }

    public ModelAndView ReturnView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        return modelAndView;
    }
}
