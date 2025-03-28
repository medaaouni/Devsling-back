package com.assignement.car_sales_garage.domain.dtos;

import com.assignement.car_sales_garage.enums.FuelType;
import com.assignement.car_sales_garage.enums.Transmission;
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
    private LocalDateTime registrationDate;
    private Integer price;
    private FuelType fuelType;
    private Integer mileage;
    private Transmission transmission;
}
