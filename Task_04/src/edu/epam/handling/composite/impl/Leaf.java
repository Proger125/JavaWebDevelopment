package edu.epam.handling.composite.impl;

import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.exception.HandlerException;

import java.util.List;

public class Leaf implements TextComponent {
    private final char value;
    private final ComponentType type;

    public Leaf(char value, ComponentType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public void add(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, TextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addAll(List<TextComponent> components) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getComponentsAmount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public TextComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TextComponent> getChildren() throws HandlerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
