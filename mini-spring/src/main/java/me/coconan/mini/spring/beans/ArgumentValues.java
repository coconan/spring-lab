package me.coconan.mini.spring.beans;

import java.util.*;

public class ArgumentValues {
    private final List<ArgumentValue> argumentValueList = new ArrayList<>();

    public ArgumentValues() {
    }

    public void addArgumentValue(ArgumentValue argumentValue) {
        argumentValueList.add(argumentValue);
    }

    public ArgumentValue getIndexedArgumentValue(int index) {
        return argumentValueList.get(index);
    }

    public int getArgumentCount() {
        return argumentValueList.size();
    }

    public boolean isEmpty() {
        return argumentValueList.isEmpty();
    }
}
