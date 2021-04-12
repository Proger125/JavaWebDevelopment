package edu.epam.shapes.entity;

import java.util.Objects;

public class Quadrangle {
    private long id;
    private Point upperLeft;
    private Point lowerLeft;
    private Point upperRight;
    private Point lowerRight;

    public Quadrangle(long id, Point upperLeft, Point lowerLeft, Point upperRight, Point lowerRight) {
        this.id = id;
        this.upperLeft = upperLeft;
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.lowerRight = lowerRight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Point getUpperLeft() {
        return upperLeft;
    }

    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    public Point getLowerLeft() {
        return lowerLeft;
    }

    public void setLowerLeft(Point lowerLeft) {
        this.lowerLeft = lowerLeft;
    }

    public Point getUpperRight() {
        return upperRight;
    }

    public void setUpperRight(Point upperRight) {
        this.upperRight = upperRight;
    }

    public Point getLowerRight() {
        return lowerRight;
    }

    public void setLowerRight(Point lowerRight) {
        this.lowerRight = lowerRight;
    }

    @Override
    public String toString() {
        return "Quadrangle{" +
                "id=" + id +
                ", upperLeft=" + upperLeft +
                ", lowerLeft=" + lowerLeft +
                ", upperRight=" + upperRight +
                ", lowerRight=" + lowerRight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quadrangle that = (Quadrangle) o;
        return id == that.id &&
                Objects.equals(upperLeft, that.upperLeft) &&
                Objects.equals(lowerLeft, that.lowerLeft) &&
                Objects.equals(upperRight, that.upperRight) &&
                Objects.equals(lowerRight, that.lowerRight);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id) + upperLeft.hashCode() + upperRight.hashCode() + lowerLeft.hashCode() + lowerRight.hashCode();
    }
}
