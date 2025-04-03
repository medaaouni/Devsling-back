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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
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
        CarResponseDto carResponseDto = TestDataUtil.createCarResponseDto();

        when(carMapper.toDto(any(Car.class))).thenReturn(carResponseDto);

        when(carRepository.findAll(any(Specification.class))).thenReturn(expected);

        assertThat(underTest.getCarsByFuelTypeAndMaxPrice(FuelType.DIESEL, 15000)).hasSize(1);

        verify(carRepository).findAll(any(Specification.class));

    }

    @Test
    void getCarsByFuelTypeAndMaxPrice_shouldFilterByFuelTypeOnly_whenPriceIsNull() {
        List<Car> expected = List.of(TestDataUtil.createCarEntity());
        CarResponseDto carResponseDto = TestDataUtil.createCarResponseDto();
        carResponseDto.setPrice(null);
        when(carMapper.toDto(any(Car.class))).thenReturn(carResponseDto);

        when(carRepository.findAll(any(Specification.class))).thenReturn(expected);

        assertThat(underTest.getCarsByFuelTypeAndMaxPrice(FuelType.HYBRID, 100)).hasSize(1);
    }

    @Test
    public void whenGetCarsByFuelTypeAndMaxPrice_withNoMatchingCar_thenReturnEmptyList() {

        when(carRepository.findAll(any(Specification.class))).thenReturn(List.of());

        assertThat(underTest.getCarsByFuelTypeAndMaxPrice(FuelType.HYBRID, 100)).hasSize(0);

        verify(carRepository).findAll(any(Specification.class));

    }

    @Test
    public void whenGetMakes_thenReturnAllAvailableMakes() {

        List<String> expected = List.of("Mercedes", "Toyota");

        when(carRepository.findAllAvailableMakes()).thenReturn(expected);

        assertThat(underTest.getAllAvailableMakes()).hasSize(2);
    }


    @Test
    public void whenFileEmpty_thenThrowException() {
        MultipartFile emptyFile = Mockito.mock(MultipartFile.class);
        when(emptyFile.isEmpty()).thenReturn(true);
        assertThatThrownBy(() -> underTest.updateCarPicture(1L, emptyFile))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenFileNoValid_thenThrowException() {
        MultipartFile picture = Mockito.mock(MultipartFile.class);
        when(picture.getContentType()).thenReturn("text/plain");
        assertThatThrownBy(() -> underTest.updateCarPicture(1L, picture))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
