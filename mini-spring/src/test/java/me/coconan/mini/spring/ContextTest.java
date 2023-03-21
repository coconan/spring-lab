package me.coconan.mini.spring;

import me.coconan.mini.spring.context.ClassPathXmlApplicationContext;
import me.coconan.mini.spring.misc.ConstructorInjectionBean;
import me.coconan.mini.spring.misc.FooService;
import me.coconan.mini.spring.misc.FooServiceImpl;
import me.coconan.mini.spring.misc.PropertyInjectionBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContextTest {
    @Test
    public void test_getBean() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        FooService fooService = (FooService) context.getBean("fooService");

        assertEquals(FooServiceImpl.class.getName() + " executed", fooService.execute());
    }

    @Test
    public void test_constructor_injection() throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("constructor-injection-beans.xml");
        ConstructorInjectionBean constructorInjectionBean = (ConstructorInjectionBean) context.getBean("constructorInjectionBean");

        assertEquals("blah", constructorInjectionBean.getMessage());
        assertEquals(42, constructorInjectionBean.getSecret());
    }

    @Test
    public void test_property_injection() throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("property-injection-beans.xml");
        PropertyInjectionBean propertyInjectionBean = (PropertyInjectionBean) context.getBean("propertyInjectionBean");

        assertEquals("blah", propertyInjectionBean.getMessage());
        assertEquals(42, propertyInjectionBean.getSecret());
    }
}
