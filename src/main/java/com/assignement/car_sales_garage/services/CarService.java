package com.assignement.car_sales_garage.services;

import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.domain.dtos.CarResponseDto;
import com.assignement.car_sales_garage.enums.FuelType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {
    CarResponseDto addCar(CarRequestDto carRequestDto);
    List<CarResponseDto> getCarsByFuelTypeAndMaxPrice(FuelType fuelType, Integer price);
    List<String> getAllAvailableMakes();
    CarResponseDto updateCarPicture(Long carId, MultipartFile picture) throws IOException;

}
