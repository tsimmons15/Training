package dev.simmons.models;

import java.util.Objects;

public class Jelly {
    private String flavor;

    public Jelly() {

    }

    public Jelly(String flavor) {
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jelly jelly = (Jelly) o;
        return Objects.equals(flavor, jelly.flavor);
    }

    @Override
    public int hashCode() {
        return Objects.hash("Jelly Doughnut", flavor);
    }

    @Override
    public String toString() {
        return flavor + " Jelly";
    }
}
