package dev.simmons.service;

import dev.simmons.entities.Car;

import java.util.List;

public interface CarRentalService {
    List<Car> getAllCars();
    Car getCarById(int id);

    Car saveCar(Car car);

    boolean deleteCar(int id);
}
