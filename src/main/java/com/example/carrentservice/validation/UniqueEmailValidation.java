package com.example.carrentservice.validation;

import com.example.carrentservice.models.Customer;
import com.example.carrentservice.repository.CarDAO;
import com.example.carrentservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidation implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private CustomerService customerService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return customerService == null ? true : customerService.findByEmail(email) == null;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }
}
