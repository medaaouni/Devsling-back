package com.assignement.car_sales_garage.controllers;


import com.assignement.car_sales_garage.TestDataUtil;
import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.services.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
public class CarControllerIT {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final CarService carService;


    @Autowired
    public CarControllerIT(MockMvc mockMvc, CarService carService) {
        this.mockMvc = mockMvc;
        this.carService = carService;
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
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
        );

    }

    @Test
    public void whenRequestIsValid_thenCreateCar() throws Exception {

        CarRequestDto carRequest = TestDataUtil.createCarRequestDto();
        String carJson = objectMapper.writeValueAsString(carRequest);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

    }

    @Test
    public void whenGetCarsByFuelTypeAndMaxPrice_thenReturnFilteredCars() throws Exception {

        CarRequestDto carRequest = TestDataUtil.createCarRequestDto();
        carService.addCar(carRequest);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars")
                        .param("fuelType", "DIESEL")
                        .param("maxPrice", "15000")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].price").value(15000)
        );
    }

    @Test
    public void whenGetCarsByInvalidFuelTypeAndMaxPrice_thenReturnBadRequest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars")
                        .param("fuelType", "invalidType")
                        .param("maxPrice", "15000")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );
    }

    @Test
    public void whenGetMakes_thenReturnAllAvailableMakes() throws Exception {

        CarRequestDto carRequest = TestDataUtil.createCarRequestDto();
        carService.addCar(carRequest);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars/makes")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.length()").value(1)
        );

    }

}
