package com.example.carrentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class CarRentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentServiceApplication.class, args);
    }

}
