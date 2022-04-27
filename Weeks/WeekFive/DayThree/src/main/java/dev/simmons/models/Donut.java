package dev.simmons.models;

import java.util.Objects;

public class Donut {
    private String name;
    private double cost;
    private Jelly filling;

    public Donut() {
    }

    public Donut(String name, double cost, Jelly filling) {
        this.name = name;
        this.cost = cost;
        this.filling = filling;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Jelly getFilling() {
        return filling;
    }

    public void setFilling(Jelly filling) {
        this.filling = filling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donut donut = (Donut) o;
        return Double.compare(donut.cost, cost) == 0 && Objects.equals(name, donut.name) && Objects.equals(filling, donut.filling);
    }

    @Override
    public int hashCode() {
        return Objects.hash("Donut", name, cost, filling);
    }

    @Override
    public String toString() {
        return "Doughnut " + name + ", $" + cost + " with " + filling;
    }
}
