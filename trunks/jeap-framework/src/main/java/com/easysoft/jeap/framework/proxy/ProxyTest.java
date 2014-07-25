package com.easysoft.jeap.framework.proxy;

import java.lang.reflect.Proxy;

/**
 * @author : andy.huang
 * @since :
 */
public class ProxyTest {
    public static void main(String[] args) {
        /*Subject subject = new ProxySubject();
        subject.request();*/
        Subject subject = new ConSubject();
        Subject proxySubject = (Subject)Proxy.newProxyInstance(subject.getClass().getClassLoader(),subject.getClass().getInterfaces(),new DynamicSubject(subject));
        proxySubject.request();
    }
}
