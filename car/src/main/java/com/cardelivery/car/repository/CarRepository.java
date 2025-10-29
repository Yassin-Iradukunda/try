package com.cardelivery.car.repository;

import com.cardelivery.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByColor(String car);
    boolean existsByColor(String color);
}
