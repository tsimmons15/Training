package Functional;

import java.util.ArrayList;
import java.util.List;

public class LambdaFest {
    public static void main(String[] args) {
        List<Integer> tempsFahrenheit = new ArrayList<>();
        tempsFahrenheit.add(101);
        tempsFahrenheit.add(30);
        tempsFahrenheit.add(50);
        tempsFahrenheit.add(57);
        tempsFahrenheit.add(0);

        tempsFahrenheit.stream().filter(t -> t > 32)
                .map(t -> (t - 32) * 5.0/9)
                .forEach(System.out::println);
    }
}
