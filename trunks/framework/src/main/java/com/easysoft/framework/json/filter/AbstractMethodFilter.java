package com.easysoft.framework.json.filter;

import net.sf.json.util.PropertyFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * // 先实现一个abstract类，将读取Bean属性的Method找到并传递给子类处理
 * User: andy
 * Date: 14-4-1
 * Time: 下午5:55
 *
 * @since:
 */
public abstract class AbstractMethodFilter implements PropertyFilter {
    private static final Log logger = LogFactory.getLog(AbstractMethodFilter.class);
    // 这个方法留给子类实现，以便适应不同的过滤需求
    public abstract boolean apply(final Method method);

    public boolean apply(final Object source, final String name, final Object value) {
        if (source instanceof Map) {
            return false;
        }
        String propName = name.substring(0, 1).toUpperCase() + name.substring(1);
        Class clz = source.getClass();
        String methodName = "get" + propName;
        Method method = null;
        try {
            method = clz.getMethod(methodName, (Class[]) null);   // 寻找属性的get方法
        } catch (NoSuchMethodException nsme) {
            String methodName2 =  "is" + propName;                // 也许是个is方法
            try {
                method = clz.getMethod(methodName2, (Class[]) null);
            } catch (NoSuchMethodException ne) {
                // 没有找到属性的get或者is方法，打印错误，返回true
                logger.error("No such methods: "+ methodName + " or " + methodName2);
                return true;
            }
        }
        return apply(method);
    }

}
