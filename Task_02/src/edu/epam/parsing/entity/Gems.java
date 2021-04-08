package edu.epam.parsing.entity;

import java.util.ArrayList;

public class Gems {
    private ArrayList<Gem> list = new ArrayList<>();

    public void setList(ArrayList<Gem> list) {
        this.list = list;
    }
    public void add(Gem gem){
        list.add(gem);
    }

    @Override
    public String toString() {
        return "Gems{" +
                "list=" + list +
                '}';
    }
}
