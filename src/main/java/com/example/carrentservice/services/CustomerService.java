package com.example.carrentservice.services;

import com.example.carrentservice.models.Customer;
import com.example.carrentservice.repository.CustomerDAO;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService{

    private CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer findById(Long id) {
        return customerDAO.findById(id).get();
    }

    public Customer findByEmail(String email) {
        Optional<Customer> customer = customerDAO.findByEmail(email);
        return customer.isEmpty() ? null : customer.get();
    }

    
    public Customer findCustomerByFullName(String fullName) { return customerDAO.findCustomerByFullName(fullName); }

    
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    
    public Customer findCustomerByActive(boolean active) { return customerDAO.findCustomerByActive(true); }

    
    public void save(Customer customer) {
        customerDAO.save(customer);
    }
}
