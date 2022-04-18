package reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionPlayground {
    public static void main(String[] args) {
        System.out.println(MathCalculator.add(1,2));
        Class<MathCalculator> mathClass = MathCalculator.class;
        System.out.println("Methods ------------");
        for (Method m: mathClass.getMethods()) {
            System.out.println(m);
        }
        System.out.println("Declared Methods ---");
        for (Method m : mathClass.getDeclaredMethods()) {
            System.out.println(m);
        }
        System.out.println("Fields -------------");
        for (Field f : mathClass.getFields()) {
            System.out.println(f);
        }
        System.out.println("Declared Fields ----");
        for (Field f : mathClass.getDeclaredFields()) {
            System.out.println(f);
        }

        try {
            System.out.println(MathCalculator.pi);
            Field f = mathClass.getField("pi");
            f.setDouble(null, 5);
            System.out.println(MathCalculator.pi);
        } catch (NoSuchFieldException | IllegalAccessException nsfe) {
            System.out.println(nsfe);
        }

        HelloWorld.setUp();
        System.out.println(HelloWorld.greet("testing"));
    }
}

class MathCalculator{
    public static final double pi = 3.14;
    public static double add(double num1, double num2) {
        return num1 + num2;
    }
    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }
}

class HelloWorld {
    @Default
    public static String greeting;
    public static String greet(String name) {
        return greeting + name;
    }
    public static void setUp() {
        try {
            Field field = HelloWorld.class.getField("greeting");
            field.set(null, "Hello, ");

            for (Annotation an : field.getDeclaredAnnotations()) {
                System.out.println(an);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}