package collections;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class IteratorPlayground {
    public static void main(String[] args) {
        Range r = new Range(10);

        for (Integer i : r) {
            System.out.println(i);
        }
    }

}

class Range implements Iterable<Integer> {

    private int size;
    private int location;

    public Range(int size) {
        this.size = size;
        this.location = 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        Iterator<Integer> iter = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return location < size;
            }

            @Override
            public Integer next() {
                int spot = location++;
                return spot;
            }

            @Override
            public void remove() {
                Iterator.super.remove();
            }

            @Override
            public void forEachRemaining(Consumer<? super Integer> action) {
                Iterator.super.forEachRemaining(action);
            }
        };

        return iter;
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Iterable.super.spliterator();
    }
}