package dev.simmons.utilities.lists;

public class LinkedList<T  extends Comparable<T>> implements List<T> {
    private Link<T> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public LinkedList(T... items) {
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

        Link<T> newItem = new Link<T>();
        newItem.setData(item);

        if (this.head == null) {
            this.head = newItem;
            size = 1;
            return;
        }

        Link<T> curr = this.head;
        Link<T> next = curr.getNext();
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

        Link<T> curr = this.head;

        size--;

        if (index == 0) {
            this.head = curr.getNext();
            return curr.getData();
        }

        while (curr != null && --index > 0) {
            curr = curr.getNext();
        }

        Link<T> next = curr.getNext();
        curr.setNext(next.getNext());

        return next.getData();
    }

    @Override
    public boolean remove(T item) {
        if (item == null) {
            return false;
        }

        boolean removed = false;


        return removed;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Given: " + index + " List size: " + size);
        }

        Link<T> curr = this.head;
        while (curr != null && index-- > 0) {
            curr = curr.getNext();
        }

        return curr.getData();
    }

    public int get(T item) {
        if (item == null) {
            return -1;
        }

        int i = 0;
        Link<T> curr = this.head;
        while (curr != null && curr.getData().compareTo(item) == 0) {
            curr = curr.getNext();
            i++;
        }

        if (curr == null) {
            return -1;
        }

        return i;
    }
}