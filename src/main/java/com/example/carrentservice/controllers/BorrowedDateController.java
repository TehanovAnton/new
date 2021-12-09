package com.example.carrentservice.controllers;

import com.example.carrentservice.models.*;
import com.example.carrentservice.services.BorrowedDateService;
import com.example.carrentservice.services.CarService;
import com.example.carrentservice.services.CustomerService;
import com.example.carrentservice.services.MailService;
import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
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
@RequestMapping
@SessionAttributes({ "customer", "borrowedDate" })
public class BorrowedDateController {
    private CarService carService;
    private BorrowedDateService borrowedDateService;
    private CustomerService customerService;
    private MailService mailService;
    private Car carById;
    private Customer customer;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public BorrowedDateController(MailService mailService, CarService carService, BorrowedDateService borrowedDateService, CustomerService customerService) {
        this.carService = carService;
        this.borrowedDateService = borrowedDateService;
        this.customerService = customerService;
        this.mailService = mailService;
    }

    @GetMapping(value = "/borrowedDates")
    public ModelAndView borrowedDates(Model model) {
        if (customer == null)
            customer = customerService.findCustomerByActive(true);

        List<Car> customerBorrowedCars = carService.findAllByCustomerId(customer.getId());
        List<BorrowedDate> customerBorrowedDates = borrowedDateService.findAllByCustomerId(customer.getId());
        List<ShowBorrowedDate> dates = showCustomerBorrowedDates(customerBorrowedDates, customerBorrowedCars);

        model.addAttribute("borrowedDates", dates);
        return new ModelAndView("ShowBooks");
    }

    @DeleteMapping(path = { "/id}" })
    public ModelAndView delete(Model model,
                               @RequestParam("book_id") Long bookId) {
        borrowedDateService.deleteById(bookId);
        return new ModelAndView("redirect:/borrowedDates");
    }

    @GetMapping(value = "/customer/book/edit{book_id}")
    public ModelAndView editBorrowedDatePage(Model model,
                                     @RequestParam(value="start_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                 String start_date,
                                     @RequestParam(value="end_date",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                 String end_date,
                                     @RequestParam("book_id") Long bookId) {
        BorrowedDate borrowedDate = borrowedDateService.findById(bookId);
        Car car = carService.findById(borrowedDate.getCar().getId()).get();
        List<AvailableCarsResult> carsByDates =
                borrowedDateService.checkAvailableCars(borrowedDate.getStartDate(), borrowedDate.getEndDate());

        String start_date_attr = start_date != null ? start_date : borrowedDate.startDateFormatted();
        String end_date_attr = end_date != null ? end_date : borrowedDate.endDateFormatted();

        model.addAttribute("borrowedDate", borrowedDate);
        model.addAttribute("start_date", start_date_attr);
        model.addAttribute("end_date", end_date_attr);

        model.addAttribute("availableCars", carsByDates);
        model.addAttribute("car", car);

        return new ModelAndView("EditBorrowedDate");
    }


    @PostMapping(value="/book/update")
    public ModelAndView editBorrowedDate(Model model,
                                    @RequestParam(value="car_id")
                                            Long carId,
                                    @RequestParam(value="start_date") @DateTimeFormat(pattern = "yyyy-MM-dd")
                                            Calendar start_date,
                                    @RequestParam(value="end_date") @DateTimeFormat(pattern = "yyyy-MM-dd")
                                             Calendar end_date,
                                    @RequestParam("book_id")
                                             Long bookId) {
        BorrowedDate borrowedDate = borrowedDateService.findById(bookId);
        borrowedDate.setStartDate(start_date);
        borrowedDate.setEndDate(end_date);
        borrowedDate.setCar(carService.findById(carId).get());
        borrowedDateService.save(borrowedDate);

        return new ModelAndView("redirect:/customer/books");
    }


    @PostMapping(value = "/check_dates")
    public ModelAndView check_dates(Model model,
                                    @RequestParam(value = "book_id") Long book_id,
                                    @RequestParam(value = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar startDate,
                                    @RequestParam(value = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar endDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return new ModelAndView("redirect:/customer/book/edit?book_id=" + book_id +
                "&start_date=" + format.format(startDate.getTime()) + "&end_date=" + format.format(endDate.getTime()));
    }


    private List<ShowBorrowedDate> showCustomerBorrowedDates(List<BorrowedDate> customerBorrowedDates, List<Car> customerBorrowedCars) {
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
        carById = carService.findById(carId).get();

        initCustomerPartOne(customer, carById);

        BorrowedDate borrowedDate = new BorrowedDate();
        initBorrowedDate(borrowedDate, carById, customer, startDate, endDate);

        model.addAttribute("car_id", carId);
        model.addAttribute("customer", customer);
        model.addAttribute("borrowedDate", borrowedDate);

        return new ModelAndView("redirect:/bookPartTwo?car_id=" + carId.toString());
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
        return new ModelAndView("redirect:/bookResume?car_id=" + carId);
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
