package com.example.carrentservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BorrowedDateControllerAspect {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.carrentservice.controllers.BorrowedDateController.borrowedDates*(..))")
    public void borrowedDates(){}

    @After("borrowedDates() && args(carId)")
    public void afterAdvice(JoinPoint joinPoint, Long carId) {
        System.out.println("delete borrowedDate: " + carId.toString());
    }
}
