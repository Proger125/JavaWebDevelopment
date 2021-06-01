package edu.epam.handling.composite.impl;

import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.Delimeter;
import edu.epam.handling.exception.HandlerException;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private List<TextComponent> textComponents = new ArrayList<>();
    private ComponentType type;

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public void add(int index, TextComponent component) {
        textComponents.add(index, component);
    }

    @Override
    public void addAll(List<TextComponent> components) {
        textComponents.addAll(components);
    }

    @Override
    public void remove(TextComponent textComponent) {
        textComponents.remove(textComponent);
    }

    @Override
    public void clear() {
        this.textComponents.clear();
    }

    @Override
    public int getComponentsAmount() {
        return textComponents.size();
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public TextComponent getChild(int index) throws HandlerException {
        if (index < 0 || index > textComponents.size() - 1){
            throw new HandlerException("Incorrect index");
        }
        return textComponents.get(index);
    }

    @Override
    public List<TextComponent> getChildren() throws HandlerException {
        return List.copyOf(textComponents);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        switch (type){
            case TEXT -> {
                for (var component : textComponents){
                    builder.append(component).append(Delimeter.ENTER.getValue());
                }
                builder.deleteCharAt(builder.length() - 1);
            }
            case PARAGRAPH -> {
                //builder.append(Delimeter.TAB);
                for (var component : textComponents){
                    builder.append(component);
                }
                builder.deleteCharAt(builder.length() - 1);
            }
            case SENTENCE -> {
                int i = 0;
                for (var component : textComponents){
                    builder.append(component).append(Delimeter.SPACE.getValue());
                    i++;
                }
            }
            default -> {
                for (var component : textComponents){
                    builder.append(component);
                }
            }
        }
        return builder.toString();
    }
}
