package me.coconan.mini.spring.beans;

import me.coconan.mini.spring.core.Resource;
import org.dom4j.Element;

public class XmlBeanDefinitionReader {
    private final SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    public void loadBeanDefinition(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);
            simpleBeanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
