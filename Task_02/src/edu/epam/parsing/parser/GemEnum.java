package edu.epam.parsing.parser;

public enum GemEnum {
    GEMS("gems"),
    NATURAL_GEM("natural-gem"),
    ARTIFICIAL_GEM("artificial-gem"),
    VISUAL_PARAMETERS("visual-parameters"),
    ID("id"),
    WEIGHT("weight"),
    NAME("name"),
    PRECIOUSNESS("preciousness"),
    CREATION_DATE("creation-date"),
    EXTRACTION_PLACE("extraction-place"),
    GROWING_TIME("growing-time"),
    COLOR("color"),
    TRANSPARENCY("transparency"),
    EDGE_AMOUNT("edge-amount");
    private String value;
    private GemEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
