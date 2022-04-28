package dev.simmons.repos;

import dev.simmons.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

}
