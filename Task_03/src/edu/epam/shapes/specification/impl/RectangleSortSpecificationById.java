package edu.epam.shapes.specification.impl;

import edu.epam.shapes.entity.Rectangle;

import java.util.Comparator;

public class RectangleSortSpecificationById implements Comparator<Rectangle> {
    @Override
    public int compare(Rectangle o1, Rectangle o2) {
        return Long.compare(o1.getId(), o2.getId());
    }
}
