package com.easysoft.jeap.framework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : andy.huang
 * @since :
 */
public class DynamicSubject implements InvocationHandler {
    private Object sub;
    public DynamicSubject(){

    }
    public DynamicSubject(Object obj){
        this.sub = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke");
        method.invoke(sub,args);
        System.out.println("after invoke");
        return null;
    }
}
