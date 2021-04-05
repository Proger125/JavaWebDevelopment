package edu.epam.parsing.entity;

import edu.epam.parsing.enumeration.Origin;
import edu.epam.parsing.enumeration.Preciousness;

public abstract class Gem {
    protected String name;
    protected Preciousness preciousness;
    private class VisualParameters{
        private String color;
        private int transparency;
        private int edgeAmount;
        public VisualParameters(){

        }
    }
}
