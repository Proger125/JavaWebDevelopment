package edu.epam.handling.composite;

public interface Component {
    void add(Component component);
    void remove(Component component);
    ComponentType getType();
    Object getChild(int index);
}
