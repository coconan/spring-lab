package me.coconan.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AgentDecorator implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.print("Hello, ");

        Object retVal = invocation.proceed();

        System.out.println(" aop");
        
        return retVal;
    }
}
