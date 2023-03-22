package me.coconan.mini.spring.beans;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
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
            Object bean;
            ArgumentValues argumentValues = beanDefinition.getConstructorArgumentValues();
            if (argumentValues.isEmpty()) {
                bean = clz.newInstance();
            } else {
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
                    bean = constructor.newInstance(paramValues);
                } catch (NoSuchMethodException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }

            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if (propertyValues.isEmpty()) {
                return bean;
            }
            for (int i = 0; i < propertyValues.size(); i++) {
                PropertyValue propertyValue = propertyValues.getIndexedPropertyValue(i);
                Class<?>[] paramTypes = new Class<?>[1];
                Object[] paramValues = new Object[1];
                if ("String".equals(propertyValue.getType()) || "java.lang.String".equals(propertyValue.getType())) {
                    paramTypes[0] = String.class;
                    paramValues[0] = propertyValue.getValue().toString();
                } else if ("Integer".equals(propertyValue.getType()) || "java.lang.Integer".equals(propertyValue.getType())) {
                    paramTypes[0] = Integer.class;
                    paramValues[0] = Integer.valueOf(propertyValue.getValue().toString());
                } else if ("int".equals(propertyValue.getType())) {
                    paramTypes[0] = int.class;
                    paramValues[0] = Integer.valueOf(propertyValue.getValue().toString());
                } else {
                    paramTypes[0] = String.class;
                    paramValues[0] = propertyValue.getValue().toString();
                }

                String propertyName = propertyValue.getName();
                String methodName = String.format("set%s%s", propertyName.substring(0, 1).toUpperCase(), propertyName.substring(1));
                try {
                    Method method = clz.getMethod(methodName, paramTypes);
                    method.invoke(bean, paramValues);
                } catch (NoSuchMethodException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }

            return bean;
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
