package com.example.carrentservice.services;

import com.example.carrentservice.models.BorrowedDate;
import com.example.carrentservice.models.Car;
import com.example.carrentservice.models.Customer;

public interface MailService {

    void sendMailTest();

    void sendMail(Customer customer, BorrowedDate borrowedDate, Car car);
}
