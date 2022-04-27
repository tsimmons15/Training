package dev.simmons.configs;

import dev.simmons.models.Donut;
import dev.simmons.models.Jelly;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DonutConfig {
    @Bean(name = "StrawberryJelly")
    public Jelly strawberry() {
        return new Jelly("Strawberry");
    }

    @Bean(name="Banana")
    public Jelly blueberry() {
        return new Jelly("Blueberry");
    }

    @Bean(name = "Banana")
    public Jelly banana() {
        return new Jelly("Banana");
    }

    @Bean(name = "Strawberry")
    public Donut strawberryDonut() {
        return new Donut("Strawberry Boom", 14.30, this.strawberry());
    }

    @Bean(name = "Blueberry")
    public Donut blueberryDonut() {
        return new Donut("Blueberry Bloom", 10.59, this.blueberry());
    }
}
