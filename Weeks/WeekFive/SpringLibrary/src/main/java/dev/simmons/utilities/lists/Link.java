package dev.simmons.utilities.lists;

public class Link<T> {
    private Link<T> next;
    private T data;

    Link() {
        next = null;
        data = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Link<T> getNext() {
        return next;
    }
    public void setNext(Link<T> next) {
        this.next = next;
    }
}
