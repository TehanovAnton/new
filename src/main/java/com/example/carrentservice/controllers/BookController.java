package com.example.carrentservice.controllers;

import com.example.carrentservice.models.*;
import com.example.carrentservice.services.BorrowedDateService;
import com.example.carrentservice.services.CarService;
import com.example.carrentservice.services.CustomerService;
import com.example.carrentservice.services.MailService;
import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@EnableEmailTools
@SessionAttributes({ "customer", "borrowedDate" })
@RequestMapping
public class BookController {

    private CarService carService;
    private BorrowedDateService borrowedDateService;
    private CustomerService customerService;
    private MailService mailService;
    private Car carById;
    private Customer customer;

    public BookController(MailService mailService, CarService carService, BorrowedDateService borrowedDateService, CustomerService customerService) {
        this.carService = carService;
        this.borrowedDateService = borrowedDateService;
        this.customerService = customerService;
        this.mailService = mailService;
    }

    @GetMapping(value = "/customer/books")
    public ModelAndView showBooks(Model model) {
        if (customer == null)
            customer = customerService.findCustomerByActive(true);

        List<Car> customerBorrowedCars = carService.findAllByCustomerId(customer.getId());
        List<BorrowedDate> customerBorrowedDates = borrowedDateService.findAllByCustomerId(customer.getId());
        List<ShowBorrowedDate> dates = showCustomerBooks(customerBorrowedDates, customerBorrowedCars);

        model.addAttribute("borrowedDates", dates);
        return new ModelAndView("ShowBooks");
    }

    private List<ShowBorrowedDate> showCustomerBooks(List<BorrowedDate> customerBorrowedDates, List<Car> customerBorrowedCars) {
        List<ShowBorrowedDate> dates = new ArrayList<ShowBorrowedDate>();
        for (int i = 0; i < customerBorrowedCars.size(); i++) {
            dates.add(new ShowBorrowedDate(customerBorrowedDates.get(i), customerBorrowedCars.get(i)));
        }
        return dates;
    }

    @GetMapping(value = "/bookPartOne{car_id}")
    public ModelAndView checkDates(Model model,
                                   @RequestParam(value = "car_id") Long carId,
                                   @RequestParam(value = "start_date", defaultValue = "2000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar startDate,
                                   @RequestParam(value = "end_date", defaultValue = "2000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar endDate) {

        carById = carService.findById(carId).get();
        List<AvailableCarsResult> availableCarById = borrowedDateService.checkAvailableCarById(startDate, endDate, carId);

        
        model.addAttribute("carById", carById);
        model.addAttribute("availableCarById", availableCarById);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("start_date", format.format(startDate.getTime()));
        model.addAttribute("end_date", format.format(endDate.getTime()));
        
        return new ModelAndView("bookPartOne");
    }

    @PostMapping(value = "/bookPartOne")
    public ModelAndView createNewCustomer(Model model,
                                          @RequestParam(value = "start_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar startDate,
                                          @RequestParam(value = "end_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar endDate,
                                          @RequestParam(value = "car_id") Long carId) {
        customer = customerService.findCustomerByActive(true);
        initCustomerPartOne(customer, carById);

        BorrowedDate borrowedDate = new BorrowedDate();
        initBorrowedDate(borrowedDate, carById, customer, startDate, endDate);

        model.addAttribute("car_id", carId);
        model.addAttribute("customer", customer);
        model.addAttribute("borrowedDate", borrowedDate);

        String url = "redirect:/bookPartTwo?car_id=" + carId.toString();
        return new ModelAndView(url);
    }

    @GetMapping(value = "bookPartTwo{car_id}")
    public ModelAndView showSessionCar(Model model, BorrowedDate borrowedDate) {
        model.addAttribute("carById", carById);
        model.addAttribute("customer", customer);
        model.addAttribute("borrowedDate", borrowedDate);
        return new ModelAndView("bookPartTwo");
    }

    @PostMapping(value = "bookPartTwo")
    public ModelAndView realizePayment(Model model,
                                       BorrowedDate borrowedDate,
                                       @RequestParam(value = "car_id") Long carId,
                                       @RequestParam(value = "cardNumber") String cardNumber,
                                       @RequestParam(value = "cvv") String cvv) {

        long days = borrowedDateService.countDays(borrowedDate);
        initCustomer(customer, new BigDecimal(cardNumber), new BigDecimal(cvv), days);

        model.addAttribute("car_id", carId);
        String url = "redirect:/bookResume?car_id=" + carId;
        return new ModelAndView(url);
    }

    @GetMapping(value = "bookResume{car_id}")
    public ModelAndView showCustomerResume(Model model, BorrowedDate borrowedDate) {
        model.addAttribute("carById", carById);
        model.addAttribute("customer", customer);
        model.addAttribute("borrowedDate", borrowedDate);
        return new ModelAndView("bookResume");
    }

    @PostMapping(value = "bookResume")
    public ModelAndView completeAll(SessionStatus status, BorrowedDate borrowedDate) {
        borrowedDateService.save(borrowedDate);
        mailService.sendMail(customer, borrowedDate, carById);
        status.setComplete();
        return new ModelAndView("redirect:/");
    }

    private void initCustomer(Customer customer, BigDecimal cardNumber, BigDecimal cvv, long days) {
        customer.setTotalPrice(customer.getTotalPrice().multiply(new BigDecimal(days)));
        customer.setRole("ROLE_USER");
        customer.setPaid(true);
        customer.setCardNumber(cardNumber);
        customer.setCvv(cvv);
    }


    private void initCustomerPartOne(Customer customer, Car car) {
        customer.getCars().add(car);
        customer.setBorrowedCars(customer.getBorrowedCars() + 1);
        customer.setTotalPrice(customer.getTotalPrice().add(car.getPrice()));
    }

    private void initBorrowedDate(BorrowedDate borrowedDate, Car car, Customer customer, Calendar startDate, Calendar endDate) {
        borrowedDate.setCar(car);
        borrowedDate.setCustomer(customer);
        borrowedDate.setStartDate(startDate);
        borrowedDate.setEndDate(endDate);
    }
}
