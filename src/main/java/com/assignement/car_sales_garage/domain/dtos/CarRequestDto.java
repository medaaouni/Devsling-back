package com.assignement.car_sales_garage.domain.dtos;

import com.assignement.car_sales_garage.enums.FuelType;
import com.assignement.car_sales_garage.enums.Transmission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarRequestDto {
    private String make;
    private String model;
    @PastOrPresent(message = "Registration date cannot be in the future")
    @NotBlank(message = "Model is required")
    private LocalDateTime registrationDate;
    @Positive(message = "Price must be greater than zero")
    private Integer price;
    private FuelType fuelType;
    private Integer mileage;
    private Transmission transmission;
}
