package com.easysoft.framework.spring;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-23
 * Time: 下午8:24
 * To change this template use File | Settings | File Templates.
 */
public class Car {
    private String brand;
    private int maxSpeed;
    private String color;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
