package com.assignement.car_sales_garage.controllers;


import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.domain.dtos.CarResponseDto;
import com.assignement.car_sales_garage.enums.FuelType;
import com.assignement.car_sales_garage.services.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Car Management", description = "Endpoints for managing cars in the garage")
public class CarController {

    private final CarService carService;

    @PostMapping
    @Operation(
            summary = "Add a new car",
            description = "Adds a car to the catalog if registered after 2015",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Car added successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid car data")
            })
    public ResponseEntity<CarResponseDto> createCar(@Valid @RequestBody CarRequestDto carRequestDto) {
        CarResponseDto carResponseDto = carService.addCar(carRequestDto);
        return new ResponseEntity<>(carResponseDto, HttpStatus.CREATED);
    }


    @GetMapping
    @Operation(
            summary = "Get cars by fuel type and max price",
            description = "Fetches cars filtered by fuel type and max price",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cars retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "No cars found")
            }
    )
    public ResponseEntity<List<CarResponseDto>> getCarsByFuelTypeAndMaxPrice(@RequestParam @Parameter(description = "Fuel type of the car") FuelType fuelType, @RequestParam Integer maxPrice) {
        List<CarResponseDto> cars = carService.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);
        return ResponseEntity.ok(cars);
    }


    @GetMapping(path = "/makes")
    @Operation(
            summary = "Get all car makes",
            description = "Retrieves all available makes from the catalog",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Makes retrieved successfully")
            }
    )
    public ResponseEntity<List<String>> getAvailableMakes() {
        List<String> makes = carService.getAllAvailableMakes();
        return ResponseEntity.ok(makes);
    }

    @PatchMapping(path = "/{id}/picture")
    @Operation(
            summary = "Update car picture",
            description = "Allows updating the car picture",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Picture updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid file format")
            })
    public ResponseEntity<CarResponseDto> updateCarPicture(@PathVariable @Parameter(description = "ID of the car") Long id, @RequestParam("file") @Parameter(description = "Car picture file") MultipartFile picture) throws IOException {
        CarResponseDto carResponseDto = carService.updateCarPicture(id, picture);
        return ResponseEntity.ok(carResponseDto);
    }
}
