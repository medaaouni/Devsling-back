package com.assignement.car_sales_garage;

import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.domain.dtos.CarResponseDto;
import com.assignement.car_sales_garage.domain.entities.Car;
import com.assignement.car_sales_garage.enums.FuelType;
import com.assignement.car_sales_garage.enums.Transmission;

import java.time.LocalDateTime;

public final class TestDataUtil {

    public static Car createCarEntity() {
        return Car.builder()
                .make("Mercedes")
                .model("C220")
                .registrationDate(LocalDateTime.of(2020, 5, 20, 0, 0))
                .price(15000)
                .fuelType(FuelType.DIESEL)
                .mileage(60000)
                .transmission(Transmission.MANUAL)
                .picture("https://example.com/toyota-corolla.jpg")
                .build();
    }

    public static CarRequestDto createCarDtoRequestBefore2015() {

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

    public static CarRequestDto createCarRequestDto() {

        return CarRequestDto.builder()
                .make("Mercedes")
                .model("C220")
                .registrationDate(LocalDateTime.of(2020, 5, 20, 0, 0))
                .price(15000)
                .fuelType(FuelType.DIESEL)
                .mileage(60000)
                .transmission(Transmission.MANUAL)
                .build();
    }

    public static CarResponseDto createCarResponseDto() {

        return CarResponseDto.builder()
                .make("Mercedes")
                .model("C220")
                .registrationDate(LocalDateTime.of(2020, 5, 20, 0, 0))
                .price(15000)
                .fuelType(FuelType.DIESEL)
                .mileage(60000)
                .transmission(Transmission.MANUAL)
                .build();
    }
}
