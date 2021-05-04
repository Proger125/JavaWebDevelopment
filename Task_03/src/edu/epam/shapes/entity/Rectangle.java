package edu.epam.shapes.entity;

import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.observer.Observable;
import edu.epam.shapes.observer.Observer;
import edu.epam.shapes.observer.RectangleEvent;

import java.util.ArrayList;
import java.util.List;

public class Rectangle implements Observable {
    private final long id;
    private Point lowerLeftPoint;
    private int width;
    private int height;
    private List<Observer> observers = new ArrayList<>();

    public Rectangle(long id, Point lowerLeftPoint, int width, int height) {
        this.id = id;
        this.lowerLeftPoint = lowerLeftPoint;
        this.width = width;
        this.height = height;
    }

    public long getId() {
        return id;
    }


    public Point getLowerLeftPoint() {
        return lowerLeftPoint;
    }

    public void setLowerLeftPoint(Point lowerLeftPoint) {
        this.lowerLeftPoint = lowerLeftPoint;
        notifyObservers();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        notifyObservers();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        notifyObservers();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Rectangle{ ");
        builder.append("Id: ").append(id).append(", ");
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
        return this.id == that.id
                && this.lowerLeftPoint.equals(that.lowerLeftPoint)
                && this.width == that.width
                && this.height == that.height;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id) + lowerLeftPoint.hashCode() + Integer.hashCode(width) + Integer.hashCode(height);
    }

    @Override
    public void attach(Observer observer) throws ShapeException {
        if (observer != null){
            observers.add(observer);
        }else{
           throw new ShapeException("Observer is null");
        }
    }

    @Override
    public void detach(Observer observer) throws ShapeException {
        if (observer != null){
            observers.remove(observer);
        }else{
            throw new ShapeException("Observer is null");
        }
    }

    @Override
    public void notifyObservers() {
        if (!observers.isEmpty()){
            RectangleEvent event = new RectangleEvent(this);
            for (Observer observer : observers){
                observer.parameterChanged(event);
            }
        }
    }
}
