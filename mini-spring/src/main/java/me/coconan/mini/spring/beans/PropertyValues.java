package me.coconan.mini.spring.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    private final List<PropertyValue> propertyValueList;

    public PropertyValues() {
        propertyValueList = new ArrayList<>();
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public int size() {
        return propertyValueList.size();
    }

    public boolean isEmpty() {
        return propertyValueList.isEmpty();
    }

    public PropertyValue getIndexedPropertyValue(int i) {
        return propertyValueList.get(i);
    }
}
