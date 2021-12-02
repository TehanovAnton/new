package com.example.carrentservice.services;

import com.example.carrentservice.models.Customer;
import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(Long id);

    void save(Customer customer);
}
