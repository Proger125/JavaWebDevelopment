package edu.epam.parsing.entity;

import java.time.YearMonth;

public abstract class Gem {
    private String id;
    private int weight;
    private String name;
    private Preciousness preciousness;
    private VisualParameters parameters;
    private YearMonth creationDate;

    public Gem(){
        parameters = new VisualParameters();
    }
    public Gem(String id, int weight, String name, Preciousness preciousness, VisualParameters parameters, YearMonth creationDate) {
        this.id = id;
        this.weight = weight;
        this.name = name;
        this.preciousness = preciousness;
        this.parameters = parameters;
        this.creationDate = creationDate;
    }

    public int getValue() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gem gem = (Gem) o;
        return id.equals(gem.id) &&
                name.equals(gem.name) &&
                preciousness == gem.preciousness &&
                parameters.equals(gem.parameters) &&
                creationDate.equals(gem.creationDate) &&
                weight == gem.weight;
    }

    @Override
    public int hashCode() {
        return id.hashCode()
                + name.hashCode()
                + preciousness.hashCode()
                + parameters.hashCode()
                + creationDate.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Gem{ ");
        builder.append("id = ").append(id).append(" ");
        builder.append("name = ").append(name).append(" ");
        builder.append("preciousness = ").append(preciousness).append(" ");
        builder.append("parameters = ").append(parameters).append(" ");
        builder.append("creationDate = ").append(creationDate).append(" ");
        builder.append("}");
        return builder.toString();
    }

    public static class VisualParameters{
        private String color;
        private int transparency;
        private int edgeAmount;

        public VisualParameters(){}
        public VisualParameters(String color, int transparency, int edgeAmount) {
            this.color = color;
            this.transparency = transparency;
            this.edgeAmount = edgeAmount;
        }

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
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            VisualParameters that = (VisualParameters) o;
            return transparency == that.transparency &&
                    edgeAmount == that.edgeAmount &&
                    color.equals(that.color);
        }

        @Override
        public int hashCode() {
            return color.hashCode()
                    + Integer.hashCode(transparency)
                    + Integer.hashCode(edgeAmount);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("VisualParameters{ ");
            builder.append("color = ").append(color).append(" ");
            builder.append("transparency = ").append(transparency).append(" ");
            builder.append("edgeAmount = ").append(edgeAmount).append(" ");
            builder.append("}");
            return builder.toString();
        }
    }
}
