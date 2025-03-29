package com.assignement.car_sales_garage.controllers;


import com.assignement.car_sales_garage.TestDataUtil;
import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.services.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class CarControllerIT {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;


    public CarControllerIT(MockMvc mockMvc, CarService carService) {
        this.mockMvc = mockMvc;
//        this.carService = carService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void whenRequestIsInValid_thenRejectCar() throws Exception {

        CarRequestDto carRequest = TestDataUtil.createCarDtoRequestBefore2015();
        String carJson = objectMapper.writeValueAsString(carRequest);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        )
        ;


    }
}
