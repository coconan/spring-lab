package me.coconan.mini.spring.misc;

public class ConstructorPropertyInjectionMixedBean {
    private String message;
    private int secret;
    private String name;
    private int weight;

    public ConstructorPropertyInjectionMixedBean(String message, int secret) {
        this.message = message;
        this.secret = secret;
    }

    public String getMessage() {
        return message;
    }

    public int getSecret() {
        return secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
