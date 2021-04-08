package edu.epam.parsing.entity;

import edu.epam.parsing.enumeration.Preciousness;

import java.time.YearMonth;
import java.util.Objects;

public class ArtificialGem extends Gem{
    private int growingType;

    public ArtificialGem() {super();}

    public ArtificialGem(int id, String name, Preciousness preciousness, VisualParameters parameters, YearMonth creationDate, int growingType) {
        super(id, name, preciousness, parameters, creationDate);
        this.growingType = growingType;
    }

    public int getGrowingType() {
        return growingType;
    }

    public void setGrowingType(int growingType) {
        this.growingType = growingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArtificialGem that = (ArtificialGem) o;
        return growingType == that.growingType;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Integer.hashCode(growingType);
    }

    @Override
    public String toString() {
        return "ArtificialGem{" +
                "growingType=" + growingType +
                '}';
    }
}
