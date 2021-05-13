package edu.epam.handling.composite.impl;

import edu.epam.handling.composite.Component;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.Delimeter;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    private List<Component> components = new ArrayList<>();
    private ComponentType type;

    public Composite(ComponentType type) {
        this.type = type;
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public Object getChild(int index) {
        return components.get(index);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        switch (type){
            case TEXT -> {
                for (var component : components){
                    builder.append(component).append(Delimeter.ENTER);
                }
            }
            case PARAGRAPH -> {
                builder.append(Delimeter.TAB);
                for (var component : components){
                    builder.append(component).append(Delimeter.SPACE);
                }
            }
            case SENTENCE -> {
                for (var component : components){
                    builder.append(component).append(Delimeter.SPACE);
                }
            }
            default -> {
                for (var component : components){
                    builder.append(component);
                }
            }
        }
        return builder.toString();
    }
}
