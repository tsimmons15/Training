package collections;

import java.util.List;
import java.util.ArrayList;

public class ListPlayground {
    public static void main(String[] args) {
        List<Person> peopleList = new ArrayList<>();
        Person adam = new Person("adam", "ranieri", 35);
        Person bill = new Person("bill", "Kris", 25);
        Person ted = new Person("ted", "smith", 25);
        peopleList.add(adam);
        peopleList.add(bill);
        peopleList.add(ted);
        peopleList.add(adam);
        peopleList.add(bill);
        peopleList.add(ted);
        peopleList.add(adam);
        peopleList.add(bill);
        peopleList.add(ted);

        System.out.println("Full list------------");
        for (Person p : peopleList) {
            System.out.println(p);
        }
        System.out.println("---------------------");
        System.out.println("Getting fourth person");
        System.out.println(peopleList.get(3));
        System.out.println("---------------------");

    }
}
