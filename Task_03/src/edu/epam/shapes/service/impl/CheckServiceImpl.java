package edu.epam.shapes.service.impl;

import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.service.CheckService;

public class CheckServiceImpl implements CheckService {

    @Override
    public boolean isSquare(Rectangle rectangle) {
        return rectangle.getWidth() == rectangle.getHeight();
    }
}
