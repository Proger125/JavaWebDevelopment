package edu.epam.shapes.specification.impl;

import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.specification.RectangleSpecification;

public class RectangleIdSpecification implements RectangleSpecification {
    private long id;
    public RectangleIdSpecification(long id){
        this.id = id;
    }
    @Override
    public boolean specified(Rectangle rectangle) {
        return rectangle.getId() == id;
    }
}
