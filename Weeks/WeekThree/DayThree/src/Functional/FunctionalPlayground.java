package Functional;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

public class FunctionalPlayground {
    public static void main(String[] args) {
        Greeter greeter = new EnglishGreeting();
        greeter.greet("Adam");
        Greeter gerGreeter = new GermanGreeting();
        gerGreeter.greet("Adam");

        Greeter langGreeting = (String name) -> {
            System.out.println("Lang, " + name + "!");
        };

        langGreeting.greet("Jaque");


        List<String> names = new ArrayList<>();
        names.add("Kim");
        names.add("Janet");
        names.add("Adam");

        names.forEach(s -> System.out.println("Greetings, " + s + "!"));
        Consumer<String> explicit = (String name) -> {
            System.out.println("Greetings, " + name + "!");
        };
        names.forEach(explicit);


    }
}
