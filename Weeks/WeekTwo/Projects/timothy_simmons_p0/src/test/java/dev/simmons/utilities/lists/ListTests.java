package dev.simmons.utilities.lists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ListTests {
    @Test
    void addItems() {
        List<String> names = new LinkedList();
        names.add("A");
        names.add("B");
        names.add("C");
        Assertions.assertEquals(3, names.length());
    }

    @Test
    void getByIndex() {
        List<String> names = new LinkedList();
        names.add("A");
        names.add("B");
        names.add("C");
        String result = names.get(1);
        Assertions.assertEquals("B", result);
    }

    @Test
    void manyAdds() {
        List<String> names = new LinkedList();

        for (int i = 0; i < 10000; i++) {
            names.add("test");
        }

        Assertions.assertEquals(10000, names.length());
    }

    @Test
    public void removeByIndex() {
        List<String> names = new LinkedList();
        names.add("A");
        names.add("B");
        names.add("C");
        names.add("D");
        String result = names.remove(1);
        Assertions.assertEquals("B", result);
        result = names.remove(2);
        Assertions.assertEquals("D", result);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> names.remove(-1));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> names.remove(55));

        result = names.remove(0);
        Assertions.assertEquals("A", result);
        result = names.remove(0);
        Assertions.assertEquals("C", result);

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> names.remove(0));
    }

    @Test
    public void removeByItem() {
        List<String> names = new LinkedList();
        names.add("A");
        names.add("B");
        names.add("C");
        names.add("D");
        Assertions.assertTrue(names.remove("B"));
        Assertions.assertTrue(names.remove("D"));

        Assertions.assertFalse(names.remove("D"));
        Assertions.assertFalse(names.remove("E"));

        Assertions.assertTrue(names.remove("A"));
        Assertions.assertTrue(names.remove("C"));

        Assertions.assertFalse(names.remove("A"));
    }
}
