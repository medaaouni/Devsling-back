package com.assignement.car_sales_garage.mapper;

import com.assignement.car_sales_garage.domain.dtos.CarRequestDto;
import com.assignement.car_sales_garage.domain.dtos.CarResponseDto;
import com.assignement.car_sales_garage.domain.entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {
    CarResponseDto toDto(Car car);
    Car toEntity(CarRequestDto carRequestDto);
}
