package com.easysoft.framework.spring;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-23
 * Time: 下午8:18
 * To change this template use File | Settings | File Templates.
 */
public class CarFactoryBean implements FactoryBean {
    private String carInfo;

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    @Override
    public Object getObject() throws Exception {
        System.out.println("返回由FactoryBean创建的Bean实例");
        String[] infos = carInfo.split(",");
        Car car = new Car();
        car.setBrand(infos[0]);
        car.setMaxSpeed(Integer.parseInt(infos[1]));
        car.setColor(infos[2]);
        return car;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
