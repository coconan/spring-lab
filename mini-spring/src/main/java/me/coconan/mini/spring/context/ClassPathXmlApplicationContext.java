package me.coconan.mini.spring.context;

import me.coconan.mini.spring.beans.*;
import me.coconan.mini.spring.core.ClassPathXmlResource;
import me.coconan.mini.spring.core.Resource;

public class ClassPathXmlApplicationContext implements BeanFactory {
    private BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        beanFactory = new SimpleBeanFactory();
        Resource resource = new ClassPathXmlResource(fileName);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(resource);
    }

    @Override
    public Object getBean(String beanName) throws NoSuchBeanDefinitionException {
        return beanFactory.getBean(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanDefinition);
    }
}
