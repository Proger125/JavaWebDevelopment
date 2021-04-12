package edu.epam.parsing.entity;

import edu.epam.parsing.enumeration.ExtractionPlace;
import edu.epam.parsing.enumeration.Preciousness;

import java.time.YearMonth;
import java.util.Objects;

public class NaturalGem extends Gem{
    private ExtractionPlace place;

    public NaturalGem() {}

    public NaturalGem(String id, int value, String name, Preciousness preciousness, VisualParameters parameters, YearMonth creationDate, ExtractionPlace place) {
        super(id, value, name, preciousness, parameters, creationDate);
        this.place = place;
    }

    public ExtractionPlace getPlace() {
        return place;
    }

    public void setPlace(ExtractionPlace place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NaturalGem that = (NaturalGem) o;
        return place == that.place;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + place.hashCode();
    }

    @Override
    public String toString() {
        return "NaturalGem{" +
                "place=" + place +
                '}';
    }
}
