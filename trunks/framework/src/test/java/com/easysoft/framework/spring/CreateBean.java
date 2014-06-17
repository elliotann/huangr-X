package com.easysoft.framework.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-23
 * Time: 下午10:07
 * To change this template use File | Settings | File Templates.
 */
public class CreateBean {
    public static void main(String[] args) {
        ApplicationContext cxt = new ClassPathXmlApplicationContext(
                "com/elliot/framework/spring/beans.xml");
        Car car = (Car)cxt.getBean("car");
        System.out.println(car.getBrand());
    }

}
