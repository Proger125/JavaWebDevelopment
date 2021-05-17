package edu.epam.handling.composite;

import edu.epam.handling.exception.HandlerException;

public interface Component {
    void add(Component component);
    void remove(Component component);
    ComponentType getType();
    Object getChild(int index) throws HandlerException;
}
