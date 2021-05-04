package edu.epam.shapes.repository.impl;

import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.repository.RectangleRepository;
import edu.epam.shapes.specification.RectangleSpecification;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RectangleRepositoryImpl implements RectangleRepository {
    private static Logger logger = LogManager.getLogger();
    private static final RectangleRepositoryImpl instance = new RectangleRepositoryImpl();
    private final List<Rectangle> list = new ArrayList<>();

    public static RectangleRepositoryImpl getInstance(){
        return instance;
    }
    @Override
    public void add(Rectangle rectangle) {
        list.add(rectangle);
        logger.log(Level.INFO, "Rectangle was added to repository");
    }

    @Override
    public void addAll(List<Rectangle> rectangles) {
        list.addAll(rectangles);
        logger.log(Level.INFO, "All rectangles form list were added to repository");
    }

    @Override
    public void remove(Rectangle rectangle) {
        list.remove(rectangle);
        logger.log(Level.INFO, "Rectangle was removed from repository");
    }

    @Override
    public void removeAll(List<Rectangle> rectangles) {
        list.removeAll(rectangles);
        logger.log(Level.INFO, "All rectangles from list were removed from repository");
    }

    @Override
    public void clear() {
        list.clear();
        logger.log(Level.INFO, "Repository is clear");
    }

    @Override
    public void set(int index, Rectangle rectangle) {
        list.set(index, rectangle);
    }

    @Override
    public Rectangle get(int index) {
        return list.get(index);
    }

    @Override
    public List<Rectangle> sort(Comparator<Rectangle> comparator) {
        logger.log(Level.INFO, "Repository return sorted list");
        return list.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public List<Rectangle> query(RectangleSpecification specification) {
        List<Rectangle> result = new ArrayList<>();
        for (Rectangle rectangle : list){
            if (specification.specified(rectangle)){
                result.add(rectangle);
            }
        }
        logger.log(Level.INFO, "Repository return query");
        return result;
    }

    @Override
    public List<Rectangle> queryStream(RectangleSpecification specification) {
        logger.log(Level.INFO, "Repository return query");
        return list.stream().filter(specification::specified).collect(Collectors.toList());
    }
}
