package com.easysoft.framework.utils;

import com.easysoft.framework.db.NotDbField;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;


public class ReflectionUtil {

    private Class cls;
    /**
     * 传过来的对象
     */
    private Object obj;

    /**
     * 存放get方法
     */
    private Hashtable<String, Method> getMethods = null;
    /**
     * 存放set方法
     */
    private Hashtable<String, Method> setMethods = null;

    /**
     * 定义构造方法 -- 一般来说是个pojo
     *
     * @param o
     *            目标对象
     */
    public ReflectionUtil(Object o) {
        obj = o;
        initMethods();
    }

    /**
     *
     * @desc 初始化
     */
    public void initMethods() {
        getMethods = new Hashtable<String, Method>();
        setMethods = new Hashtable<String, Method>();
        cls = obj.getClass();
        Method[] methods = cls.getMethods();
        // 定义正则表达式，从方法中过滤出getter / setter 函数.
        String gs = "get(\\w+)";
        Pattern getM = Pattern.compile(gs);
        String ss = "set(\\w+)";
        Pattern setM = Pattern.compile(ss);
        // 把方法中的"set" 或者 "get" 去掉
        String rapl = "$1";
        String param;
        for (int i = 0; i < methods.length; ++i) {
            Method m = methods[i];
            String methodName = m.getName();
            if (Pattern.matches(gs, methodName)) {
                param = getM.matcher(methodName).replaceAll(rapl).toLowerCase();
                getMethods.put(param, m);
            } else if (Pattern.matches(ss, methodName)) {
                param = setM.matcher(methodName).replaceAll(rapl).toLowerCase();
                setMethods.put(param, m);
            } else {
                // org.jeecgframework.core.util.LogUtil.info(methodName + " 不是getter,setter方法！");
            }
        }
    }

    /**
     *
     * @desc 调用set方法
     */
    public boolean setMethodValue(String property,Object object) {
        Method m = setMethods.get(property.toLowerCase());
        if (m != null) {
            try {
                // 调用目标类的setter函数
                m.invoke(obj, object);
                return true;
            } catch (Exception ex) {

                return false;
            }
        }
        return false;
    }

    /**
     *
     * @desc 调用set方法
     */
    public Object getMethodValue(String property) {
        Object value=null;
        Method m = getMethods.get(property.toLowerCase());
        if (m != null) {
            try {
                /**
                 * 调用obj类的setter函数
                 */
                value=m.invoke(obj, new Object[] {});

            } catch (Exception ex) {

            }
        }
        return value;
    }

    public static Object invokeMethod(String className, String methodName,
                                      Object[] args) {

        try {

            Class serviceClass = Class.forName(className);
            Object service = serviceClass.newInstance();

            Class[] argsClass = new Class[args.length];
            for (int i = 0, j = args.length; i < j; i++) {
                argsClass[i] = args[i].getClass();
            }

            Method method = serviceClass.getMethod(methodName, argsClass);
            return method.invoke(service, args);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object newInstance(String className,Object... _args ){

        try {
            Class[] argsClass = new Class[_args.length];

            for (int i = 0, j = _args.length; i < j; i++) {

                if(_args[i]==null){
                    argsClass[i]=null;
                }
                else{

                    argsClass[i] = _args[i].getClass();
                }
            }


            Class newoneClass  = Class.forName(className);
            Constructor cons = newoneClass.getConstructor(argsClass);

            Object obj= cons.newInstance(_args);
            return obj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return null;

    }

    /**
     * 将po对象中有属性和值转换成map
     *
     * @param po
     * @return
     */
    public static Map po2Map(Object po) {
        Map poMap = new HashMap();
        Map map = new HashMap();
        try {
            map = org.apache.commons.beanutils.BeanUtils.describe(po);
        } catch (Exception ex) {
        }
        Object[] keyArray = map.keySet().toArray();
        for (int i = 0; i < keyArray.length; i++) {
            String str = keyArray[i].toString();
            if (str != null && !str.equals("class")) {
                if (map.get(str) != null) {
                    poMap.put(str, map.get(str));
                }
            }
        }

        Method[] ms =po.getClass().getMethods();
        for(Method m:ms){
            String name = m.getName();

            if(name.startsWith("get")||name.startsWith("is")){
                if(m.getAnnotation(NotDbField.class)!=null){
                    poMap.remove(getFieldName(name));
                }
            }

        }
        return poMap;
    }

    private static String getFieldName(String methodName){
        if(methodName.startsWith("get")){
            methodName = methodName.substring(3);
        }else if(methodName.startsWith("is")){
            methodName = methodName.substring(2);
        }

        methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
        return methodName;
    }
	
}
