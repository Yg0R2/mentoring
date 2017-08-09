package com.epam.hcomtest.resource;

public enum ResourceColor {

    BLUE("blue"), DEFAULT(""), GREEN("green"), RED("red");

    private final String value;

    ResourceColor(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
