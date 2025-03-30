package com.assignement.car_sales_garage.controllers;


import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.domain.dtos.CarResponseDto;
import com.assignement.car_sales_garage.enums.FuelType;
import com.assignement.car_sales_garage.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping(path = "/makes")
    public ResponseEntity<List<String>> getAvailableMakes() {
        List<String> makes = carService.getAllAvailableMakes();
        return ResponseEntity.ok(makes);
    }

    @PatchMapping(path = "/{id}/picture")
    public ResponseEntity<CarResponseDto> updateCarPicture(@PathVariable Long id, @RequestParam("file") MultipartFile picture) throws IOException {
        CarResponseDto carResponseDto = carService.updateCarPicture(id, picture);
        return ResponseEntity.ok(carResponseDto);
    }
}
