package me.coconan.mini.spring;

import me.coconan.mini.spring.context.ClassPathXmlApplicationContext;
import me.coconan.mini.spring.misc.*;
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

    @Test
    public void test_constructor_property_injection_mixed() throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("constructor-property-injection-mixed-beans.xml");
        ConstructorPropertyInjectionMixedBean bean =
                (ConstructorPropertyInjectionMixedBean) context.getBean("constructorPropertyInjectionMixedBean");

        assertEquals("blah", bean.getMessage());
        assertEquals(42, bean.getSecret());
        assertEquals("walle", bean.getName());
        assertEquals(3, bean.getWeight());
    }

    @Test
    public void test_ref_property_injection_cyclic_dependency() throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("ref-property-injection-beans.xml");
        DummyService dummyService = (DummyService) context.getBean("dummyService");

        assertEquals("hello", dummyService.execute());
    }
}
