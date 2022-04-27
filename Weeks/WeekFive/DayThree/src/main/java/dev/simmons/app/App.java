package dev.simmons.app;

import dev.simmons.configs.DonutConfig;
import dev.simmons.models.Donut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DonutConfig.class);
        Donut donut = ac.getBean("Blueberry", Donut.class);
        System.out.println(donut);
    }
}
