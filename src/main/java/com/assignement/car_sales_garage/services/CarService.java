package com.assignement.car_sales_garage.services;

import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.domain.dtos.CarResponseDto;
import com.assignement.car_sales_garage.domain.entities.Car;

public interface CarService {
    CarResponseDto addCar(CarRequestDto carRequestDto);
}
