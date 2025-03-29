package com.assignement.car_sales_garage.services;


import com.assignement.car_sales_garage.TestDataUtil;
import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.domain.dtos.CarResponseDto;
import com.assignement.car_sales_garage.domain.entities.Car;
import com.assignement.car_sales_garage.enums.FuelType;
import com.assignement.car_sales_garage.exceptions.InvalidCarException;
import com.assignement.car_sales_garage.mapper.CarMapper;
import com.assignement.car_sales_garage.repositories.CarRepository;
import com.assignement.car_sales_garage.services.impl.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CarServiceTests {

    @InjectMocks
    CarServiceImpl underTest;

    @Mock
    CarRepository carRepository;

    @Mock
    CarMapper carMapper;

    @Test
    public void whenRegisterCarBefore2015_thenRejectCar() {

        CarRequestDto oldCar = TestDataUtil.createCarDtoRequestBefore2015();

        assertThatThrownBy(() -> underTest.addCar(oldCar))
                .isInstanceOf(InvalidCarException.class)
                .hasMessageContaining("Car registered before 2015 is not allowed");
    }

    @Test
    public void whenRegisterCarAfter2015_thenSaveCar() {
        CarRequestDto carRequest = TestDataUtil.createCarRequestDto();
        CarResponseDto carResponseDto = TestDataUtil.createCarResponseDto();
        Car expected = TestDataUtil.createCarEntity();

        when(carMapper.toEntity(any(CarRequestDto.class))).thenReturn(expected);
        when(carMapper.toDto(any(Car.class))).thenReturn(carResponseDto);
        when(carRepository.save(eq(expected))).thenReturn(expected);

        assertThat(underTest.addCar(carRequest)).isEqualTo(carResponseDto);

    }


    @Test
    public void whenGetCarsByFuelTypeAndMaxPrice_thenReturnFilteredCars() {

        List<Car> expected = List.of(TestDataUtil.createCarEntity());

        when(carRepository.findByFuelTypeAndPriceLessThanEqual(eq(FuelType.DIESEL),eq(15000))).thenReturn(expected);

        assertThat(underTest.getCarsByFuelTypeAndMaxPrice(FuelType.DIESEL,15000)).hasSize(1);
    }


}
