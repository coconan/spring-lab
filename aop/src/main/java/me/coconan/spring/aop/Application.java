package me.coconan.spring.aop;

import org.springframework.aop.framework.ProxyFactory;

public class Application {
    public static void main(String[] args) {
        Agent target = new Agent();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new AgentDecorator());
        pf.setTarget(target);

        Agent proxy = (Agent) pf.getProxy();

        target.speak();
        System.out.println("");
        proxy.speak();
    }
}