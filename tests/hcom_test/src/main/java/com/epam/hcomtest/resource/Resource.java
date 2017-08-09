package com.epam.hcomtest.resource;

public class Resource implements Comparable<Resource> {

    private ResourceColor color;
    private int key;
    private String value;

    public Resource(int key, String value) {
        this(key, value, ResourceColor.DEFAULT);
    }

    public Resource(int key, String value, ResourceColor color) {
        this.key = key;
        this.value = value;
        this.color = color;
    }

    public ResourceColor getColor() {
        return color;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(Resource o) {
        int valueCompareResult = getValue().compareToIgnoreCase(o.getValue());
        //int valueCompareResult = getValue().compareTo(o.getValue());

        if ((valueCompareResult == 0) && (getKey() < o.getKey())) {
            return -1;
        }
        else if ((valueCompareResult == 0) && (getKey() > o.getKey())) {
            return 1;
        }

        return valueCompareResult;
    }

}
