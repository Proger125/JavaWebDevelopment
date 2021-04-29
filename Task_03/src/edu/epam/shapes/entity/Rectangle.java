package edu.epam.shapes.entity;

import java.util.Objects;

public class Rectangle {
    private long id;
    private Point lowerLeftPoint;
    private int width;
    private int height;

    public Rectangle(long id, Point lowerLeftPoint, int width, int height) {
        this.id = id;
        this.lowerLeftPoint = lowerLeftPoint;
        this.width = width;
        this.height = height;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Point getLowerLeftPoint() {
        return lowerLeftPoint;
    }

    public void setLowerLeftPoint(Point lowerLeftPoint) {
        this.lowerLeftPoint = lowerLeftPoint;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Rectangle{ ");
        builder.append("Lower left point: ").append(lowerLeftPoint).append(", ");
        builder.append("Width: ").append(width).append(", ");
        builder.append("Height: ").append(height).append(" ");
        builder.append("}");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle that = (Rectangle) o;
        return id == that.id
                && this.lowerLeftPoint.equals(that.lowerLeftPoint)
                && this.width == that.width
                && this.height == that.height;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id) + lowerLeftPoint.hashCode() + Integer.hashCode(width) + Integer.hashCode(height);
    }
}
