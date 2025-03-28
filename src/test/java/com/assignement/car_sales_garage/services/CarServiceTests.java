package com.assignement.car_sales_garage.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
public class CarServiceTests {

    @Test
    public void shouldRejectCarsRegisteredAfter2015() {

        Car oldCar = new Car(...,LocalDateTime.of(2024, 3, 28, 10, 30, 0));

        assertThatThrownBy(() -> carService.addCar(oldCar))
                .isInstanceOf(InvalidCarException.class)
                .hasMessageContaining("Invalid Year");
    }


}
