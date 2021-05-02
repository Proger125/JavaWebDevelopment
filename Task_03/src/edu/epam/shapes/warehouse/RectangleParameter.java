package edu.epam.shapes.warehouse;


public class RectangleParameter {
    private int perimeter;
    private int square;

    public RectangleParameter(int perimeter, int square) {
        this.perimeter = perimeter;
        this.square = square;
    }

    public int getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RectangleParameter that = (RectangleParameter) o;
        return perimeter == that.perimeter &&
                square == that.square;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(perimeter) + Integer.hashCode(square);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Rectangle Parameters{ ");
        builder.append("Perimeter: ").append(perimeter).append(", ");
        builder.append("Square: ").append(square).append(" ");
        builder.append("}");
        return builder.toString();
    }
}
