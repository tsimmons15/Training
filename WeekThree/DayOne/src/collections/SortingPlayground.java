package collections;

import java.util.Set;
import java.util.TreeSet;

public class SortingPlayground {
    public static void main(String[] args) {
        Person adam = new Person("adam", "ranieri", 35);
        Person bill = new Person("bill", "Kris", 25);
        Person ted = new Person("ted", "smith", 25);
        Person other  = new Person("rob", "smith", 25);

        Set<Person> peopleSet = new TreeSet<>();
        peopleSet.add(adam);
        peopleSet.add(other);
        peopleSet.add(bill);
        peopleSet.add(ted);

        for (Person p : peopleSet) {
            System.out.println(p);
        }
    }
}
