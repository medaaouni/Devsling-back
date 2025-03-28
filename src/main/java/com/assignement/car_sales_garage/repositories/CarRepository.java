package com.assignement.car_sales_garage.repositories;

import com.assignement.car_sales_garage.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
