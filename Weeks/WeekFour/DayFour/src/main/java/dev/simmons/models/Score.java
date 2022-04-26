package dev.simmons.models;

public class Score {
    private String initials;
    private int points;

    public Score() {
        initials = "";
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String toString() {
        return initials + " : " + points;
    }
}
