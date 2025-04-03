package com.assignement.car_sales_garage.repositories.specifications;

import com.assignement.car_sales_garage.domain.entities.Car;
import com.assignement.car_sales_garage.enums.FuelType;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecifications {

    public static Specification<Car> hasFuelType(FuelType fuelType) {
        return (root, query, criteriaBuilder) -> {
            if (fuelType == null) return null;
            return criteriaBuilder.equal(root.get("fuelType"), fuelType);
        };
    };

    public static Specification<Car> hasPriceLessThan(Integer price) {
        return (root, query, criteriaBuilder) -> {
            if (price == null) return null;
            return criteriaBuilder.lessThan(root.get("price"), price);
        };
    }
}
