package com.cardelivery.car.service;

import com.cardelivery.car.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCar();
    Car createCar(Car currentCar);
    void deleteCar(int carId);
    Car updateCar(int id, Car currentCar);
    Car getCar(int id);


}
