package com.example.carrentservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void allCarsUnauthorizedRequestStatus() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void carUnauthorizedRequestStatus() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/car?id=0"))
                .andExpect(status().is3xxRedirection());
    }
}
