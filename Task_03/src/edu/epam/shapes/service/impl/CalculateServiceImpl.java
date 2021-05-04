package edu.epam.shapes.service.impl;

import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.service.CalculateService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalculateServiceImpl implements CalculateService {
    private static Logger logger = LogManager.getLogger();
    @Override
    public int square(Rectangle rectangle) {
        logger.log(Level.INFO, "Square was calculated");
        return rectangle.getHeight() * rectangle.getWidth();
    }

    @Override
    public int perimeter(Rectangle rectangle) {
        int halfPerimeter = rectangle.getHeight() + rectangle.getWidth();
        logger.log(Level.INFO, "Perimeter was calculated");
        return halfPerimeter * 2;
    }
}
