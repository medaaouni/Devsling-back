package com.assignement.car_sales_garage.services.impl;

import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.domain.dtos.CarResponseDto;
import com.assignement.car_sales_garage.domain.entities.Car;
import com.assignement.car_sales_garage.enums.FuelType;
import com.assignement.car_sales_garage.exceptions.InvalidCarException;
import com.assignement.car_sales_garage.mapper.CarMapper;
import com.assignement.car_sales_garage.repositories.CarRepository;
import com.assignement.car_sales_garage.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {


    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public CarResponseDto addCar(CarRequestDto carRequestDto) {
        if (carRequestDto.getRegistrationDate().getYear() < 2015) {
            throw new InvalidCarException("Car registered before 2015 is not allowed");
        } else {
            Car car = carMapper.toEntity(carRequestDto);
            Car savedCar = carRepository.save(car);
            return carMapper.toDto(savedCar) ;

        }
    }

    @Override
    public List<CarResponseDto> getCarsByFuelTypeAndMaxPrice(FuelType fuelType, Integer price) {
        List<Car> cars = carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, price);
        return cars.stream().map(carMapper::toDto).toList();
    }

    @Override
    public List<String> getAllAvailableMakes() {
        return carRepository.findAllAvailableMakes();
    }
}
