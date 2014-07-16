package com.easysoft.jeap.framework.utils;

import com.easysoft.jeap.framework.db.PageOption;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by huangxa on 2014/7/16.
 */
public class ReflectUtil {
    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        final Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                continue;
            }
        }
        return field;
    }
    public static void setFieldValue(Object obj, String fieldName, String fieldValue) {
        final Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                field.set(obj, fieldValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DateUtil du = new DateUtil();
        System.out.println(du.getClass().getName());
        Class<?> clazz1=null;
        Class<?> clazz2=null;
        Class<?> clazz3=null;
        try {
            clazz1 = Class.forName("com.easysoft.jeap.framework.utils.DateUtil");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(clazz1.getName());
        clazz2 = DateUtil.class;
        System.out.println(clazz2.getName());

        PageOption page = null;
        try {
            clazz3 = Class.forName("com.easysoft.jeap.framework.db.PageOption");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            page = (PageOption)clazz3.newInstance();
            page.setTotalCount(6);
            System.out.println(page.getTotalCount());
            Class<?>[] inters = clazz3.getInterfaces();
            for(Class clazz:inters){
                System.out.println("interface = "+clazz);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Class<?> superclass = clazz3.getSuperclass();
        System.out.println("superClass="+superclass.getName());

        Constructor<?>[] cons = clazz3.getConstructors();
        for(Constructor constructor : cons){
            System.out.println(constructor);
        }
    }
}
