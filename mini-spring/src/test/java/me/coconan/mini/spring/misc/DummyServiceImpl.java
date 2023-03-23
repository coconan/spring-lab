package me.coconan.mini.spring.misc;

public class DummyServiceImpl implements DummyService {
    private String message;
    private int secret;
    private String name;
    private int weight;

    private BazService bazService;

    public DummyServiceImpl(String message, int secret) {
        this.message = message;
        this.secret = secret;
    }

    public String execute() {
        return "hello";
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

    public BazService getBazService() {
        return bazService;
    }

    public void setBazService(BazService bazService) {
        this.bazService = bazService;
    }
}
