package com.easysoft.jeap.framework.proxy;

/**
 * @author : andy.huang
 * @since :
 */
public class ConSubject implements Subject {
    @Override
    public void request() {
        System.out.println("I am conSubject");
    }
}
