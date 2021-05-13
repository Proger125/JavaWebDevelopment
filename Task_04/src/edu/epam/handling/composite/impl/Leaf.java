package edu.epam.handling.composite.impl;

import edu.epam.handling.composite.Component;
import edu.epam.handling.composite.ComponentType;

public class Leaf implements Component {
    private final char value;
    private final ComponentType type;

    public Leaf(char value, ComponentType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public Object getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
