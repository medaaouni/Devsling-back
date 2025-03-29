package com.assignement.car_sales_garage.repositories;

import com.assignement.car_sales_garage.domain.entities.Car;
import com.assignement.car_sales_garage.enums.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, Integer Price);
}
