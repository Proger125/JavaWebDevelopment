package edu.epam.parsing.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.YearMonth;

public class NaturalGem extends Gem{
    static Logger logger = LogManager.getLogger();
    private ExtractionPlace place;

    public NaturalGem() {
        super();
        logger.log(Level.INFO, "Natural gem was created");
    }

    public NaturalGem(String id, int weight, String name, Preciousness preciousness, VisualParameters parameters, YearMonth creationDate, ExtractionPlace place) {
        super(id, weight, name, preciousness, parameters, creationDate);
        this.place = place;
        logger.log(Level.INFO, "Natural gem was created");
    }

    public ExtractionPlace getPlace() {
        return place;
    }

    public void setPlace(ExtractionPlace place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        NaturalGem that = (NaturalGem) o;
        return place == that.place;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + place.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.deleteCharAt(builder.length() - 1);
        builder.append("extractionPlace = ").append(place).append(" ");
        builder.append("}");
        return builder.toString();
    }
}
