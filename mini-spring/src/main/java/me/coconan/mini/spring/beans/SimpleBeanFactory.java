package me.coconan.mini.spring.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBeanFactory implements BeanFactory {
    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private final List<String> beanNames = new ArrayList<>();
    private final Map<String, Object> singletons = new HashMap<>();

    @Override
    public Object getBean(String beanName) throws NoSuchBeanDefinitionException {
        Object singleton = singletons.get(beanName);
        if (singleton != null) {
            return singleton;
        }

        int index = beanName.indexOf(beanName);
        if (index == -1) {
            throw new NoSuchBeanDefinitionException();
        }

        BeanDefinition beanDefinition = beanDefinitions.get(index);
        try {
            singleton = Class.forName(beanDefinition.getClassName()).newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return singleton;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.add(beanDefinition);
        beanNames.add(beanDefinition.getId());
    }
}
