package me.coconan.mini.spring;

public class ConstructorInjectionBean {
    private String message;
    private int secret;

    public ConstructorInjectionBean(String message, int secret) {
        this.message = message;
        this.secret = secret;
    }

    public String getMessage() {
        return message;
    }

    public int getSecret() {
        return secret;
    }
}
