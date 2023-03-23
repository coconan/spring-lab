package me.coconan.mini.spring.misc;

public class BarService {
    private DummyService dummyService;

    public DummyService getDummyService() {
        return dummyService;
    }

    public void setDummyService(DummyService dummyService) {
        this.dummyService = dummyService;
    }
}
