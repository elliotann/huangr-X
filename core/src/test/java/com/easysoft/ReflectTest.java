package com.easysoft;

/**
 * @author : andy.huang
 * @since :
 */
public class ReflectTest {
    public static void main(String[] args) throws NoSuchFieldException {
        Child c = new Child(new Thrid());
        c.getClass().getDeclaredField("thrid");
    }
}
