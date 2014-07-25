package com.easysoft.jeap.framework.proxy;

/**
 * @author : andy.huang
 * @since :
 */
public class ProxySubject implements Subject {
    private Subject conSubject;
    private void before(){
        System.out.println("before request");
    }
    @Override
    public void request() {
        before();
        if(conSubject==null){
            conSubject = new ConSubject();
        }
        conSubject.request();
        after();
    }
    private void after(){
        System.out.println("after request");
    }
}
