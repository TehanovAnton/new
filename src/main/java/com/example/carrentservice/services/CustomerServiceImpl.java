package com.example.carrentservice.services;

import com.example.carrentservice.models.Customer;
import com.example.carrentservice.repository.CustomerDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Customer findById(Long id) {
        return customerDAO.findById(id).get();
    }

    @Override
    public Customer findCustomerByFullName(String fullName) { return customerDAO.findCustomerByFullName(fullName); }

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerDAO.save(customer);
    }
}
