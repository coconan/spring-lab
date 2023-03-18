package me.coconan.mini.spring.context;

public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
