package com.assignement.car_sales_garage.domain.entities;

import com.assignement.car_sales_garage.enums.FuelType;
import com.assignement.car_sales_garage.enums.Transmission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
    private Integer price;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private Integer mileage;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column(name = "picture_url" ,columnDefinition = "TEXT")
    private String picture;


}
