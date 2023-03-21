package me.coconan.mini.spring.beans;

public class PropertyValue {
    private final String type;
    private final String name;
    private final Object value;

    public PropertyValue(Object value, String type, String name) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
