package collections;

import java.util.HashMap;
import java.util.Map;

public class MapPlayground {
    public static void main(String[] args) {
        Map<Person, Integer> people = new HashMap<>();
        Person adam = new Person("adam", "ranieri", 35);
        Person bill = new Person("bill", "Kris", 25);
        Person ted = new Person("ted", "smith", 25);

        Integer i = people.getOrDefault(adam, 0);
        people.put(adam, i+1);
        i = people.getOrDefault(bill, 0);
        people.put(bill, i+1);
        i = people.getOrDefault(ted, 0);
        people.put(ted, i+1);
        i = people.getOrDefault(adam, 0);
        people.put(adam, i+1);
        i = people.getOrDefault(bill, 0);
        people.put(bill, i+1);
        i = people.getOrDefault(ted, 0);
        people.put(ted, i+1);
        i = people.getOrDefault(adam, 0);
        people.put(adam, i+1);
        i = people.getOrDefault(bill, 0);
        people.put(bill, i+1);
        i = people.getOrDefault(ted, 0);
        people.put(ted, i+1);

        for (Person p : people.keySet()) {
            System.out.println(p + " -> " + people.get(p));
        }
    }
}
