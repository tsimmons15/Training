package dev.simmons.controls;

import dev.simmons.entities.Car;
import dev.simmons.service.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@Controller
public class RentalControls {
    @Autowired
    private CarRentalService service;

    @GetMapping("/cars")
    @ResponseBody
    public List<Car> getCars() {
        return service.getAllCars();
    }

    @GetMapping("/cars/{id}")
    @ResponseBody
    public Car getCarById(@PathVariable int id) {
        return service.getCarById(id);
    }

    @PostMapping("/cars")
    @ResponseBody
    public Car saveCar(@RequestBody Car car) {
        return service.saveCar(car);
    }

    @PutMapping("/cars/{id}")
    @ResponseBody
    public Car replaceCar(@PathVariable int id, @RequestBody Car car) {
        car.setId(id);
        return service.saveCar(car);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable int id) {
        service.deleteCar(id);
    }

}
