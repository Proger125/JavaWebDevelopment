package edu.epam.shapes.observer;

import edu.epam.shapes.exception.ShapeException;

public interface Observable {
    void attach(Observer observer) throws ShapeException;
    void detach(Observer observer) throws ShapeException;
    void notifyObservers();
}
