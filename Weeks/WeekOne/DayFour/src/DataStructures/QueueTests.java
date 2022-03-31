package DataStructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueueTests {
    @Test
    void addToQueue() {
        CustomQueue<Integer> q = new CustomQueue<>();
        q.add(1);
        q.add(5);
        q.add(10);
        q.add(3);
        Assertions.assertEquals(4, q.length());
        Assertions.assertEquals(false, q.isEmpty());
    }

    @Test
    void removeFromQueue() {
        CustomQueue<Integer> q = new CustomQueue<>();
        Assertions.assertEquals(null, q.poll());
        q.add(1);
        q.add(5);
        q.add(2);
        Assertions.assertEquals(1, q.poll());
        q.add(3);
        Assertions.assertEquals(5, q.poll());
        q.add(10);
        Assertions.assertEquals(2, q.poll());
        Assertions.assertEquals(3, q.poll());
        Assertions.assertEquals(10, q.poll());
        Assertions.assertEquals(null, q.poll());
    }
}
