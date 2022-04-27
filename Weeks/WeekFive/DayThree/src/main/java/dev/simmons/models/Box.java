package dev.simmons.models;

import java.util.Objects;

public class Box {
    private int value;

    public Box() {

    }

    public Box(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return value == box.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash("Box", value);
    }

    @Override
    public String toString() {
        return "Box: $" + value;
    }
}
