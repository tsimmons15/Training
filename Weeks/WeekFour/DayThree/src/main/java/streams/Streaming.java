package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streaming {
    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);
        ints.add(6);
        ints.add(7);
        ints.add(8);
        ints.add(9);
        Stream<Integer> stream = ints.stream();
        stream.map(i -> i);
        stream.distinct();
        stream.filter(i -> i.compareTo(0) >= 0);
        // etc...

    }
}
