package com.example.carrentservice.controllers;

import com.example.carrentservice.models.Car;
import com.example.carrentservice.models.CarJson;
import com.example.carrentservice.repository.CarDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private CarController controller;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarDAO carDAO;

    @Test
    public void allCars() throws Exception {
        assertTrue(controller.allCars().size() != 0);
    }

    @Test
    public void createCar() {
        Integer sizeBeforeAdd = carDAO.findAll().size();
        controller.createCar("auto_", "testAutotestAutotestAutotestAutotestAuto", new BigDecimal("100"));

        assertTrue(sizeBeforeAdd + 1 == carDAO.findAll().size());
    }

    @Test
    public void updateCar() {
        BigDecimal decimal = new BigDecimal("1000");
        Car car = controller.createCar("auto_", "testAutotestAutotestAutotestAutotestAuto", new BigDecimal("100"));
        controller.updateCar(car.getId(), "new", "newnewnewnewnewnewnew", decimal);

        car = carDAO.findById(car.getId()).get();
        assertTrue(car.getName().equals("new"));
        assertTrue(car.getDescription().equals("newnewnewnewnewnewnew"));
        assertTrue(car.getPrice().compareTo(decimal) == 0);
    }

    @Test
    public void DeleteCar() {
        Car car = carDAO.findByName("auto_").get();
        Car deletedCar = controller.deleteCar(car.getId());
        assertTrue(carDAO.findById(deletedCar.getId()).isEmpty());
    }
}