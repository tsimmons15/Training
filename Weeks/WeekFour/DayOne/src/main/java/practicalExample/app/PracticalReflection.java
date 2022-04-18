package practicalExample.app;

import practicalExample.entities.Dog;
import practicalExample.entities.Person;

public class PracticalReflection {
    public static void main(String[] args) {
        Person adam = new Person();
        adam.setId(101);
        adam.setFname("adam");
        adam.setLname("ranieri");
        adam.setAge(18);
        System.out.println(adam);

        Dog dog = new Dog("Spot");
        dog.setOwner(adam.getFname());
        dog.setAge(3);

        System.out.println(dog);
    }
}
