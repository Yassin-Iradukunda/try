package com.cardelivery.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cardelivery.car.repository.CarRepository;
import com.cardelivery.car.entity.Car;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car createCar(Car car) {
        if (carRepository.existsByColor(car.getColor())) {
            return null; // handled in controller
        }
        return carRepository.save(car);
    }

    @Override
    public Car getCar(int id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public List<Car> getAllCar() {
        return carRepository.findAll();
    }

    @Override
    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car updateCar(int id, Car updatedCar) {
        return carRepository.findById(id).map(car -> {
            car.setColor(updatedCar.getColor());
            car.setModel(updatedCar.getModel());
            car.setMake(updatedCar.getMake());
            // Add other fields as needed
            return carRepository.save(car);
        }).orElse(null);
    }
}
