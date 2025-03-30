package com.assignement.car_sales_garage.domain.dtos;

import com.assignement.car_sales_garage.enums.FuelType;
import com.assignement.car_sales_garage.enums.Transmission;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Registration date is required")
    @Schema(description = "Date when the car was registered", example = "2020-05-20")
    private LocalDateTime registrationDate;
    @Positive(message = "Price must be greater than zero")
    private Integer price;
    private FuelType fuelType;
    private Integer mileage;
    private Transmission transmission;
}
