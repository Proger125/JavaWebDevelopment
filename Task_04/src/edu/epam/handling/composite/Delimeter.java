package edu.epam.handling.composite;

public enum Delimeter {
    ENTER("\n"), TAB("\t"), SPACE(" ");
    private String value;
    private Delimeter(String value){
        this.value = value;
    }
}
