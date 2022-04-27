package dev.simmons.configs;

import dev.simmons.models.Box;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class BoxConfig {
    @Bean(name = "bigBox")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Box hiValueBox() {
        return new Box();
    }

    @Bean(name = "smallBox")
    public Box lowValueBox() {
        return new Box(5);
    }
}
