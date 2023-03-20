package me.coconan.mini.spring.beans;

import me.coconan.mini.spring.core.Resource;
import org.dom4j.Element;

import java.util.List;

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

            List<Element> constructorElements = element.elements("constructor-arg");
            ArgumentValues argumentValues = new ArgumentValues();
            for (Element e : constructorElements) {
                String type = e.attributeValue("type");
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                argumentValues.addArgumentValue(new ArgumentValue(value, type, name));
            }

            beanDefinition.setConstructorArgumentValues(argumentValues);
            simpleBeanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
