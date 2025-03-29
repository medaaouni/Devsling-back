package com.assignement.car_sales_garage.controllers;


import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.domain.dtos.CarResponseDto;
import com.assignement.car_sales_garage.enums.FuelType;
import com.assignement.car_sales_garage.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/cars")
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponseDto> createCar(@RequestBody CarRequestDto carRequestDto) {
        CarResponseDto carResponseDto = carService.addCar(carRequestDto);
        return new ResponseEntity<>(carResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CarResponseDto>> createCar(@RequestParam FuelType fuelType, @RequestParam Integer maxPrice) {
        List<CarResponseDto> cars = carService.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);
        return ResponseEntity.ok(cars);
    }
}
