package me.coconan.mini.spring.beans;

public interface BeanFactory {
    Object getBean(String beanName) throws NoSuchBeanDefinitionException;
    boolean containsBean(String beanName);
    void registerBean(String beanName, Object object);
}
