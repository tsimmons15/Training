package DataStructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

public class ListTests {

    @Test
    void addElements() {
        SortedList<Integer> list = new SortedList<>();
        list.add(1);
        Assertions.assertEquals(1, list.length());
        list.add(5);
        Assertions.assertEquals(2, list.length());
        list.add(10);
        Assertions.assertEquals(3, list.length());
        list.add(15);
        Assertions.assertEquals(4, list.length());
        Assertions.assertEquals(false, list.isEmpty());
    }

    @Test
    void insertionOrder() {
        SortedList<Integer> list = new SortedList<>();
        list.add(15);
        list.add(10);
        list.add(5);
        list.add(1);
        Assertions.assertEquals(1, list.retrieve(0));
        Assertions.assertEquals(5, list.retrieve(1));
        Assertions.assertEquals(10, list.retrieve(2));
        Assertions.assertEquals(15, list.retrieve(3));
        list.add(20);
        Assertions.assertEquals(20, list.retrieve(4));
    }

    @Test
    void testOutOfBounds() {
        SortedList<Integer> list = new SortedList<>();
        list.add(15);
        list.add(10);
        list.add(5);
        list.add(1);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.retrieve(10);
        });
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.retrieve(-5);
        });
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.retrieve(4);
        });
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.retrieve(-1);
        });
    }

    @Test
    void testEmptyList() {
        SortedList<Integer> list = new SortedList<>();
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.retrieve(10);
        });
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.retrieve(0);
        });
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.retrieve(-1);
        });
        Assertions.assertEquals(0, list.length());
        Assertions.assertEquals(true, list.isEmpty());
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.remove(0);
        });
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.remove(10);
        });
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        });
    }

    @Test
    void removeItems() {
        SortedList<Integer> list = new SortedList<>();
        list.add(15);
        list.add(10);
        list.add(5);
        list.add(1);
        Assertions.assertEquals(1, list.remove(0));
        Assertions.assertEquals(3, list.length());

        Assertions.assertEquals(5, list.remove(0));
        Assertions.assertEquals(2, list.length());

        Assertions.assertEquals(10, list.remove(0));
        Assertions.assertEquals(1, list.length());

        Assertions.assertEquals(15, list.remove(0));
        Assertions.assertEquals(0, list.length());

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            list.remove(0);
        });
        list.add(15);
        list.add(5);
        list.add(1);
        list.add(3);
        Assertions.assertEquals(1, list.remove(0));
        Assertions.assertEquals(3, list.length());

        Assertions.assertEquals(3, list.remove(0));
        Assertions.assertEquals(2, list.length());

        Assertions.assertEquals(5, list.remove(0));
        Assertions.assertEquals(1, list.length());

        Assertions.assertEquals(15, list.remove(0));
        Assertions.assertEquals(0, list.length());
    }
}
