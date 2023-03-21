package me.coconan.mini.spring.misc;

public class PropertyInjectionBean {
    private String message;
    private int secret;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSecret() {
        return secret;
    }

    public void setSecret(int secret) {
        this.secret = secret;
    }
}
