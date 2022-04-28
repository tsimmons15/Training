package dev.simmons.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="rentals")
public class Car {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="car_id")
    private int id;
    @Column(name="make")
    private String make;
    @Column(name="model")
    private String model;
    @Column(name="mileage")
    private int mileage;

    public Car() {
    }

    public Car(int id, String make, String model, int mileage) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.mileage = mileage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && mileage == car.mileage && Objects.equals(make, car.make) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, mileage);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
