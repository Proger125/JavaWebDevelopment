package edu.epam.shapes.repository;

import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.specification.RectangleSpecification;

import java.util.Comparator;
import java.util.List;

public interface RectangleRepository {
    void add(Rectangle rectangle);
    void addAll(List<Rectangle> list);
    void remove(Rectangle rectangle);
    void removeAll(List<Rectangle> list);
    void clear();
    void set(int index, Rectangle rectangle);
    Rectangle get(int index);
    List<Rectangle> sort(Comparator<Rectangle> comparator);
    List<Rectangle> query(RectangleSpecification specification);
    List<Rectangle> queryStream(RectangleSpecification specification);
}
