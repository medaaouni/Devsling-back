package com.assignement.car_sales_garage.services.impl;

import com.assignement.car_sales_garage.domain.entities.Car;
import com.assignement.car_sales_garage.exceptions.InvalidCarException;
import com.assignement.car_sales_garage.services.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    @Override
    public void addCar(Car car) {
        if (car.getRegistrationDate().getYear() < 2015) {
            throw new InvalidCarException("Car registered before 2015 is not allowed");
        }
    }
}
