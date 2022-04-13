package Functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ExamplePlayground {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(100);
        numbers.add(20);
        numbers.add(-10);
        numbers.add(-15);
        numbers.add(101);
        numbers.add(-40);
        numbers.add(0);

        Predicate<Integer> isNeg = (n) -> {return n < 0;};
        //numbers.removeIf(n -> n < 0);
        //numbers.removeIf(n -> {return n > 0;});
        System.out.println(numbers);

        numbers.forEach(n -> {
            if (n < 0) {
                Integer i = n;
                numbers.remove(n);
                numbers.add(-n);
            }
        });

        numbers.removeIf(n -> n%2==1);
        System.out.println(numbers);
    }
}
