package collections;

import java.util.HashSet;
import java.util.Set;

public class SetPlayground {
    public static void main(String[] args) {
        Set<Person> people = new HashSet<>();
        Person adam = new Person("adam", "ranieri", 35);
        Person bill = new Person("bill", "Kris", 25);
        Person ted = new Person("ted", "smith", 25);

        people.add(adam);
        people.add(bill);
        people.add(ted);
        people.add(adam);
        people.add(bill);
        people.add(ted);
        people.add(adam);
        people.add(bill);
        people.add(ted);

        System.out.println("Full set----------------");
        System.out.println(people.size());
        for (Person p : people) {
            System.out.println(p);
        }
        System.out.println("------------------------");
        System.out.println(people.contains(adam));
    }
}
