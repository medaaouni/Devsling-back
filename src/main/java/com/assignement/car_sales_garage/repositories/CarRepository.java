package com.assignement.car_sales_garage.repositories;

import com.assignement.car_sales_garage.domain.entities.Car;
import com.assignement.car_sales_garage.enums.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, Integer Price);

    @Query("SELECT DISTINCT c.make FROM Car c")
    List<String> findAllAvailableMakes();
}
