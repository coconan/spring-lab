package me.coconan.mini.spring;

import me.coconan.mini.spring.beans.BeanDefinition;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.*;

public class ClassPathXmlApplicationContext {
    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    public ClassPathXmlApplicationContext(String fileName) throws Exception {
        readXml(fileName);
        instanceBeans();
    }

    private void readXml(String fileName) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        URL xmlPath = getClass().getClassLoader().getResource(fileName);
        Document document = saxReader.read(xmlPath);
        Element rootElement = document.getRootElement();
        for (Element element : (List<Element>) rootElement.elements()) {
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            beanDefinitions.add(beanDefinition);
        }
    }

    private void instanceBeans() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            singletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
        }
    }

    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }
}
