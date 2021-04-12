package edu.epam.shapes.service;

import edu.epam.shapes.entity.Quadrangle;

public interface CheckService {
    boolean isConvex(Quadrangle quadrangle);
    boolean isSquare(Quadrangle quadrangle);
    boolean isRhombus(Quadrangle quadrangle);
    boolean isTrapezoid(Quadrangle quadrangle);
}
