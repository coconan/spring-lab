package me.coconan.mini.spring.beans;

public class BeanDefinition {
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    private String id;
    private String className;
    private ArgumentValues constructorArgumentValues;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ArgumentValues getConstructorArgumentValues() {
        return constructorArgumentValues;
    }

    public void setConstructorArgumentValues(ArgumentValues constructorArgumentValues) {
        this.constructorArgumentValues = constructorArgumentValues;
    }
}