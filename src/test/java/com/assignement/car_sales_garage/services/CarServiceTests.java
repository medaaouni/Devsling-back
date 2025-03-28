package com.assignement.car_sales_garage.services;


import com.assignement.car_sales_garage.TestDataUtil;
import com.assignement.car_sales_garage.domain.entities.Car;
import com.assignement.car_sales_garage.exceptions.InvalidCarException;
import com.assignement.car_sales_garage.services.impl.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
public class CarServiceTests {

    @InjectMocks
    CarServiceImpl underTest;

    @Test
    public void shouldRejectCarsRegisteredAfter2015() {

        Car oldCar = TestDataUtil.createCarEntity();

        assertThatThrownBy(() -> underTest.addCar(oldCar))
                .isInstanceOf(InvalidCarException.class)
                .hasMessageContaining("Car registered before 2015 is not allowed");
    }


}
