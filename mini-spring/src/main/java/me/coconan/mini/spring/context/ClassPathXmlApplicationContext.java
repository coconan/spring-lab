package me.coconan.mini.spring.context;

import me.coconan.mini.spring.beans.*;
import me.coconan.mini.spring.core.ClassPathXmlResource;
import me.coconan.mini.spring.core.Resource;

public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {
    private BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory();
        Resource resource = new ClassPathXmlResource(fileName);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(simpleBeanFactory);
        reader.loadBeanDefinition(resource);

        beanFactory = simpleBeanFactory;
    }

    @Override
    public Object getBean(String beanName) throws NoSuchBeanDefinitionException {
        return beanFactory.getBean(beanName);
    }

    @Override
    public boolean containsBean(String beanName) {
        return beanFactory.containsBean(beanName);
    }

    @Override
    public void registerBean(String beanName, Object object) {
        beanFactory.registerBean(beanName, object);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
    }
}
