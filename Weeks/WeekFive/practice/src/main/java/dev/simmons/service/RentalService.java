package dev.simmons.service;

import dev.simmons.entities.Car;
import dev.simmons.repos.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class RentalService implements CarRentalService {
    @Autowired
    private CarRepo repo;

    @Override
    public List<Car> getAllCars() {
        return repo.findAll();
    }

    @Override
    public Car getCarById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Car saveCar(Car car) {
        return repo.save(car);
    }

    @Override
    public boolean deleteCar(int id) {
        repo.deleteById(id);
        return repo.findById(id).orElse(null) == null;
    }
}
