package com.example.carrentservice.models;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingAspect{

    @Pointcut("@annotation(MyAnnotation)")
    public void greeting() {
    }

    @Around("greeting()")
    public void beforeAdvice(ProceedingJoinPoint point) throws Throwable {
        System.out.print("Привет ");
        point.proceed();
    }
}