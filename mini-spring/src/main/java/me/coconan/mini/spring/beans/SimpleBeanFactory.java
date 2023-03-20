package me.coconan.mini.spring.beans;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
        singleton = createBean(beanDefinition);
        registerSingleton(beanName, singleton);

        return singleton;
    }

    private Object createBean(BeanDefinition beanDefinition) {
        try {
            Class<?> clz = Class.forName(beanDefinition.getClassName());
            ArgumentValues argumentValues = beanDefinition.getConstructorArgumentValues();
            if (!argumentValues.isEmpty()) {
                Class<?>[] paramTypes = new Class<?>[argumentValues.getArgumentCount()];
                Object[] paramValues = new Object[argumentValues.getArgumentCount()];
                for (int i = 0; i < argumentValues.getArgumentCount(); i++) {
                    ArgumentValue argumentValue = argumentValues.getIndexedArgumentValue(i);
                    if ("String".equals(argumentValue.getType()) || "java.lang.String".equals(argumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    } else if ("Integer".equals(argumentValue.getType()) || "java.lang.Integer".equals(argumentValue.getType())) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] = Integer.valueOf(argumentValue.getValue().toString());
                    } else if ("int".equals(argumentValue.getType())) {
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf(argumentValue.getValue().toString());
                    } else {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }
                }
                try {
                    Constructor<?> constructor = clz.getConstructor(paramTypes);
                    return constructor.newInstance(paramValues);
                } catch (NoSuchMethodException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            return Class.forName(beanDefinition.getClassName()).newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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
