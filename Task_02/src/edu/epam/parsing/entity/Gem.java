package edu.epam.parsing.entity;

import edu.epam.parsing.enumeration.Preciousness;

import java.time.YearMonth;
import java.util.Objects;

public abstract class Gem {
    private int id;
    private String name;
    private Preciousness preciousness;
    private VisualParameters parameters;
    private YearMonth creationDate;

    public Gem(){}
    public Gem(int id, String name, Preciousness preciousness, VisualParameters parameters, YearMonth creationDate) {
        this.id = id;
        this.name = name;
        this.preciousness = preciousness;
        this.parameters = parameters;
        this.creationDate = creationDate;
    }

    public YearMonth getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(YearMonth creationDate) {
        this.creationDate = creationDate;
    }

    public VisualParameters getParameters() {
        return parameters;
    }

    public void setParameters(VisualParameters parameters) {
        this.parameters = parameters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Preciousness getPreciousness() {
        return preciousness;
    }

    public void setPreciousness(Preciousness preciousness) {
        this.preciousness = preciousness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gem gem = (Gem) o;
        return id == gem.id &&
                Objects.equals(name, gem.name) &&
                preciousness == gem.preciousness &&
                Objects.equals(parameters, gem.parameters) &&
                Objects.equals(creationDate, gem.creationDate);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id)
                + name.hashCode()
                + preciousness.hashCode()
                + parameters.hashCode()
                + creationDate.hashCode();
    }

    @Override
    public String toString() {
        return "Gem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preciousness=" + preciousness +
                ", parameters=" + parameters +
                ", creationDate=" + creationDate +
                '}';
    }

    public static class VisualParameters{
        private String color;
        private int transparency;
        private int edgeAmount;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getTransparency() {
            return transparency;
        }

        public void setTransparency(int transparency) {
            this.transparency = transparency;
        }

        public int getEdgeAmount() {
            return edgeAmount;
        }

        public void setEdgeAmount(int edgeAmount) {
            this.edgeAmount = edgeAmount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VisualParameters that = (VisualParameters) o;
            return transparency == that.transparency &&
                    edgeAmount == that.edgeAmount &&
                    Objects.equals(color, that.color);
        }

        @Override
        public int hashCode() {
            return color.hashCode()
                    + Integer.hashCode(transparency)
                    + Integer.hashCode(edgeAmount);
        }

        @Override
        public String toString() {
            return "VisualParameters{" +
                    "color='" + color + '\'' +
                    ", transparency=" + transparency +
                    ", edgeAmount=" + edgeAmount +
                    '}';
        }
    }
}
