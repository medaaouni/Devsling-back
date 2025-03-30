package com.assignement.car_sales_garage.services.impl;

import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.domain.dtos.CarResponseDto;
import com.assignement.car_sales_garage.domain.entities.Car;
import com.assignement.car_sales_garage.enums.FuelType;
import com.assignement.car_sales_garage.exceptions.CarNotFoundException;
import com.assignement.car_sales_garage.exceptions.InvalidCarException;
import com.assignement.car_sales_garage.mapper.CarMapper;
import com.assignement.car_sales_garage.repositories.CarRepository;
import com.assignement.car_sales_garage.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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
            return carMapper.toDto(savedCar);

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

    @Override
    public CarResponseDto updateCarPicture(Long carId, MultipartFile picture) throws IOException {

        validateImageFile(picture);

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id: " + carId));

        String base64Image = encodeToBase64(picture);
        car.setPicture(base64Image);
        Car savedCar = carRepository.save(car);
        return carMapper.toDto(savedCar);
    }

    private void validateImageFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }
    }

    private String encodeToBase64(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
