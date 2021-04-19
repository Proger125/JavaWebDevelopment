package edu.epam.parsing.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.YearMonth;

public class ArtificialGem extends Gem{
    static Logger logger = LogManager.getLogger();
    private int growingTime;

    public ArtificialGem() {
        super();
        logger.log(Level.INFO, "Artificial gem was created");
    }

    public ArtificialGem(String id, int weight, String name, Preciousness preciousness, VisualParameters parameters, YearMonth creationDate, int growingTime) {
        super(id, weight, name, preciousness, parameters, creationDate);
        this.growingTime = growingTime;
        logger.log(Level.INFO, "Artificial gem was created");
    }

    public int getGrowingTime() {
        return growingTime;
    }

    public void setGrowingTime(int growingType) {
        this.growingTime = growingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ArtificialGem that = (ArtificialGem) o;
        return growingTime == that.growingTime;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Integer.hashCode(growingTime);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.deleteCharAt(builder.length() - 1);
        builder.append("growingTime = ").append(growingTime).append(" ");
        builder.append("}");
        return builder.toString();
    }
}
