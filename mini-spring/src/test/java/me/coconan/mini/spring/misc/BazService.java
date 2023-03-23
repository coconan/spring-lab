package me.coconan.mini.spring.misc;

public class BazService {
    private BarService barService;

    public BarService getBarService() {
        return barService;
    }

    public void setBarService(BarService barService) {
        this.barService = barService;
    }
}
