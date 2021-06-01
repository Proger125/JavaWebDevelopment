package edu.epam.handling.composite;

import edu.epam.handling.exception.HandlerException;

import java.util.List;

public interface TextComponent {
    void add(TextComponent textComponent);
    void add(int index, TextComponent component);
    void addAll(List<TextComponent> components);
    void remove(TextComponent textComponent);
    void clear();
    int getComponentsAmount();
    ComponentType getType();
    TextComponent getChild(int index) throws HandlerException;
    List<TextComponent> getChildren() throws HandlerException;
}
