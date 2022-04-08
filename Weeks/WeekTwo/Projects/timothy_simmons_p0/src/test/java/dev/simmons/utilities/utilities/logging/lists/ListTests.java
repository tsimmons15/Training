package dev.simmons.utilities.utilities.logging.lists;

import dev.simmons.utilities.lists.LinkedList;
import dev.simmons.utilities.lists.List;
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
}
