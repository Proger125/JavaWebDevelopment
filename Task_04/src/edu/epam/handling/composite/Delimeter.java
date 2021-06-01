package edu.epam.handling.composite;

public enum Delimeter {
    ENTER("\n"), TAB("\t"), SPACE(" ");
    private String value;
    Delimeter(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
