package me.coconan.mini.spring;

public class FooServiceImpl implements FooService {
    @Override
    public String execute() {
        return FooServiceImpl.class.getName() + " executed";
    }
}
