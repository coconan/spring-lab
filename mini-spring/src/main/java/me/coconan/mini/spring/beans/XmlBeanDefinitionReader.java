package me.coconan.mini.spring.beans;

import me.coconan.mini.spring.core.Resource;
import org.dom4j.Element;

import java.util.ArrayList;
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

            List<Element> propertyElements = element.elements("property");
            PropertyValues propertyValues = new PropertyValues();
            List<String> refs = new ArrayList<>();
            for (Element e : propertyElements) {
                String type = e.attributeValue("type");
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                String ref = e.attributeValue("ref");
                boolean isRef = false;
                if (ref != null && !ref.isEmpty()) {
                    isRef = true;
                    value = ref;
                    refs.add(ref);
                }
                propertyValues.addPropertyValue(new PropertyValue(value, type, name, isRef));
            }
            beanDefinition.setPropertyValues(propertyValues);
            beanDefinition.setDependsOn(refs.toArray(new String[]{}));
            simpleBeanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
