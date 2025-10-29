package com.cardelivery.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cardelivery.car.entity.Car;
import com.cardelivery.car.service.CarService;
import com.cardelivery.car.repository.CarRepository;
import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @PostMapping("create")
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        if (carRepository.existsByColor(car.getColor())) {
            return ResponseEntity.badRequest().body("Car already exists");
        }

        Car newCar = carService.createCar(car);
        if (newCar != null) {
            return ResponseEntity.ok(newCar);
        } else {
            return ResponseEntity.badRequest().body("An error occurred while creating car");
        }
    }

    @GetMapping("find-all")
    public ResponseEntity<?> getAllCar() {
        List<Car> cars = carService.getAllCar();
        return ResponseEntity.ok(cars);
    }

    @PutMapping("update/{carId}")
    public ResponseEntity<?> updateCar(@PathVariable int carId, @RequestBody Car car) {
        Car currentCar = carService.getCar(carId);
        if (currentCar == null) {
            return ResponseEntity.badRequest().body("Car not found");
        }
        if (!currentCar.getColor().equals(car.getColor()) &&
                carRepository.existsByColor(car.getColor())) {
            return ResponseEntity.badRequest().body("Color already exists");
        }

        currentCar.setMake(car.getMake());
        currentCar.setModel(car.getModel());
        currentCar.setColor(car.getColor());
        currentCar.setYear(car.getYear());

        Car updatedCar = carService.createCar(currentCar);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("delete/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable int carId) {
        Car currentCar = carService.getCar(carId);
        if (currentCar == null) {
            return ResponseEntity.badRequest().body("Car not found");
        }

        carService.deleteCar(carId);
        return ResponseEntity.ok("Car deleted successfully");
    }
}
