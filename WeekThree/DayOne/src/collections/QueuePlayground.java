package collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueuePlayground {
    public static void main(String[] args) {
        Deque<Person> people = new ArrayDeque<>();
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

        System.out.println(people.peekLast());
        System.out.println(people.pop());
        people.pop();
        System.out.println(people.peekLast());
    }
}
