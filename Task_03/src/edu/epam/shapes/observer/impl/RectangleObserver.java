package edu.epam.shapes.observer.impl;

import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.observer.Observer;
import edu.epam.shapes.observer.RectangleEvent;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;
import edu.epam.shapes.warehouse.Warehouse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RectangleObserver implements Observer {
    private static Logger logger = LogManager.getLogger();
    private final CalculateService service = new CalculateServiceImpl();
    @Override
    public void parameterChanged(RectangleEvent event) {
        Rectangle rectangle = event.getSource();
        int perimeter = service.perimeter(rectangle);
        int square = service.square(rectangle);
        long id = rectangle.getId();
        try {
            Warehouse.getInstance().updateParameter(id, perimeter, square);
        } catch (ShapeException e) {
            logger.log(Level.ERROR, "There is no such element in warehouse");
        }
    }
}
