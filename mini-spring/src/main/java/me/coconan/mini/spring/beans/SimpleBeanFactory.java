package me.coconan.mini.spring.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    public SimpleBeanFactory() {
    }

    @Override
    public Object getBean(String beanName) throws NoSuchBeanDefinitionException {
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }

        int index = beanName.indexOf(beanName);
        if (index == -1) {
            throw new NoSuchBeanDefinitionException();
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        try {
            singleton = Class.forName(beanDefinition.getClassName()).newInstance();
            registerSingleton(beanName, singleton);
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return singleton;
    }

    @Override
    public boolean containsBean(String beanName) {
        return containsSingleton(beanName);
    }

    @Override
    public void registerBean(String beanName, Object object) {
        registerSingleton(beanName, object);
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
    }
}
