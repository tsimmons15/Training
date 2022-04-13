package Functional;

@FunctionalInterface
public interface Greeter {
    void greet(String name);
}

class EnglishGreeting implements Greeter {
    @Override
    public void greet(String name) {
        System.out.println("Greetings, " + name + "!");
    }
}

class GermanGreeting implements Greeter {
    @Override
    public void greet(String name) {
        System.out.println("Guten Abend, " + name + "!");
    }
}