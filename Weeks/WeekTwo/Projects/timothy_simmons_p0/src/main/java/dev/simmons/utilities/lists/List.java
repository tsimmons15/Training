package dev.simmons.utilities.lists;

public interface List<T> {
    boolean isEmpty();
    int length();
    void add(T item);
    T remove(int index);
    boolean remove(T item);
    T get(int index);
    int get(T item);
}

