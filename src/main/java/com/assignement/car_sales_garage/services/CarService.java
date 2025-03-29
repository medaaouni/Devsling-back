package com.assignement.car_sales_garage.services;

import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.domain.dtos.CarResponseDto;
import com.assignement.car_sales_garage.domain.entities.Car;
import com.assignement.car_sales_garage.enums.FuelType;

import java.util.List;

public interface CarService {
    CarResponseDto addCar(CarRequestDto carRequestDto);
    List<CarResponseDto> getCarsByFuelTypeAndMaxPrice(FuelType fuelType, Integer price);
}
