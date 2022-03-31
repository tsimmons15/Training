package DataStructures;

import java.util.ArrayList;
import java.util.List;

public class DataPlayground {
    public static void main(String[] args) {
        SortedList<Integer> list = new SortedList<>();
        list.add(5);

    }
}

interface CustList<T> {
    boolean isEmpty();
    int length();
    void add(T item);
    T remove(int index);
    T retrieve(int index);
}

interface Queue<T> {
    T poll();
    T peek();
    void add(T item);
    boolean isEmpty();
    int length();
}

interface Set<T> {
    boolean contains(T item);
    void add(T item);
    int length();
    boolean isEmpty();
}

interface Map<K,V> {

}

class CustomQueue<T> implements Queue<T>{
    private UnsortedList<T> list;

    CustomQueue() {
        // Replace once a generic List is made.
        list = new UnsortedList<>();
    }

    @Override
    public T poll() {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove(0);
    }

    @Override
    public T peek() {
        if (list.isEmpty()) {
            return null;
        }

        return list.retrieve(0);
    }

    @Override
    public void add(T item) {
        list.add(item);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int length() {
        return list.length();
    }
}

class UnsortedList<T> implements CustList<T> {
    private SingleLink<T> head;
    private int size;

    UnsortedList() {
        head = null;
        size = 0;
    }

    UnsortedList(T... items) {
        for(T item : items) {
            this.add(item);
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null items");
        }

        SingleLink<T> newItem = new SingleLink<T>();
        newItem.setData(item);

        if (this.head == null) {
            this.head = newItem;
            size = 1;
            return;
        }

        SingleLink<T> curr = this.head;
        SingleLink<T> next = curr.getNext();
        while (next != null) {
            next = next.getNext();
            curr = curr.getNext();
        }

        size++;

        newItem.setNext(next);
        curr.setNext(newItem);
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Given: " + index + " List size: " + size);
        }

        SingleLink<T> curr = this.head;

        size--;

        if (index == 0) {
            this.head = curr.getNext();
            return curr.getData();
        }

        while (curr != null && --index > 0) {
            curr = curr.getNext();
        }

        SingleLink<T> next = curr.getNext();
        curr.setNext(next.getNext());

        return next.getData();
    }

    @Override
    public T retrieve(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Given: " + index + " List size: " + size);
        }

        SingleLink<T> curr = this.head;
        while (curr != null && index-- > 0) {
            curr = curr.getNext();
        }

        return curr.getData();
    }
}

class SortedList<T extends Comparable<T>> implements CustList<T> {
    private SingleLink<T> head;
    private int size;

    SortedList() {
        head = null;
        size = 0;
    }

    SortedList(T... items) {
        for(T item : items) {
            this.add(item);
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null items");
        }

        SingleLink<T> newItem = new SingleLink<T>();
        newItem.setData(item);

        if (this.head == null) {
            this.head = newItem;
            size = 1;
            return;
        }

        SingleLink<T> curr = this.head;
        SingleLink<T> next = curr.getNext();
        while (next != null && next.getData().compareTo(item) <= 0) {
            next = next.getNext();
            curr = curr.getNext();
        }

        size++;

        if (curr == this.head && curr.getData().compareTo(item) > 0) {
            newItem.setNext(curr);
            this.head = newItem;
            return;
        }

        newItem.setNext(next);
        curr.setNext(newItem);
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Given: " + index + " List size: " + size);
        }

        SingleLink<T> curr = this.head;

        size--;

        if (index == 0) {
            this.head = curr.getNext();
            return curr.getData();
        }

        while (curr != null && --index > 0) {
            curr = curr.getNext();
        }

        SingleLink<T> next = curr.getNext();
        curr.setNext(next.getNext());

        return next.getData();
    }

    @Override
    public T retrieve(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Given: " + index + " List size: " + size);
        }

        SingleLink<T> curr = this.head;
        while (curr != null && index-- > 0) {
            curr = curr.getNext();
        }

        return curr.getData();
    }
}

class SingleLink<T> {
    private SingleLink<T> next;
    private T data;

    SingleLink() {
        next = null;
        data = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SingleLink<T> getNext() {
        return next;
    }
    public void setNext(SingleLink<T> next) {
        this.next = next;
    }
}