package com.example.carrentservice.controllers;

import com.example.carrentservice.models.Car;
import com.example.carrentservice.models.Rent;
import com.example.carrentservice.models.RentForm;
import com.example.carrentservice.repository.CarRepository;
import com.example.carrentservice.repository.RentRepository;
import com.example.carrentservice.services.RentsControllerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping
public class RentsController {
    @Autowired
    private RentRepository rentsRepository;

    @Autowired
    private CarRepository carRepository;

    private static RentsControllerServices services;

    static
    {
        services = new RentsControllerServices();
    }

    @GetMapping(value = { "/rent/new" })
    public ModelAndView New(Model model)
    {
        Car car = new Car("maclaren", 1000);
        carRepository.save(car);

        services.AddNewRentFormAttr(model);
        return services.ReturnView("RentNew");
    }

    @PostMapping(value = { "/rent/create" })
    public void Create(Model model, @ModelAttribute("rentForm") RentForm rentForm, HttpServletResponse response) throws IOException, ParseException {
        Rent rent = services.rentByForm(rentForm, carRepository);
        services.SaveRent(rentsRepository, rent);
        response.sendRedirect("/user/sign_in");
    }
}
