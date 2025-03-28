package com.assignement.car_sales_garage;

import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.domain.entities.Car;
import com.assignement.car_sales_garage.enums.FuelType;
import com.assignement.car_sales_garage.enums.Transmission;

import java.time.LocalDateTime;

public final class TestDataUtil {

    public static Car createCarEntity() {
        return Car.builder()
                .make("Toyota")
                .model("Corolla")
                .registrationDate(LocalDateTime.of(2014, 5, 20, 0, 0))
                .price(15000)
                .fuelType(FuelType.DIESEL)
                .mileage(60000)
                .transmission(Transmission.MANUAL)
                .picture("https://example.com/toyota-corolla.jpg")
                .build();
    }

    public static CarRequestDto createCarDtoRequest() {

        return CarRequestDto.builder()
                .make("Toyota")
                .model("Corolla")
                .registrationDate(LocalDateTime.of(2014, 5, 20, 0, 0))
                .price(15000)
                .fuelType(FuelType.DIESEL)
                .mileage(60000)
                .transmission(Transmission.MANUAL)
                .build();
    }
}
