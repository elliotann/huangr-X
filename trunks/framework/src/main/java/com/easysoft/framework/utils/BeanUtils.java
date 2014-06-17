package com.easysoft.framework.utils;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;

/**
 * User: andy
 * Date: 14-4-3
 * Time: 下午3:48
 *
 * @since:
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils{
    /**
     * 对象拷贝
     * 数据对象空值不拷贝到目标对象
     *
     * @param databean
     * @param tobean
     * @throws NoSuchMethodException
     * copy
     */
    public static void copyBeanNotNull2Bean(Object databean,Object tobean)throws Exception
    {
        PropertyDescriptor origDescriptors[] =
                PropertyUtils.getPropertyDescriptors(databean);
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
//          String type = origDescriptors[i].getPropertyType().toString();
            if ("class".equals(name)) {
                continue; // No point in trying to set an object's class
            }
            if (PropertyUtils.isReadable(databean, name) &&
                    PropertyUtils.isWriteable(tobean, name)) {
                try {
                    Object value = PropertyUtils.getSimpleProperty(databean, name);
                    if(value!=null){
                        copyProperty(tobean, name, value);
                    }
                }
                catch (java.lang.IllegalArgumentException ie) {
                    ; // Should not happen
                }
                catch (Exception e) {
                    ; // Should not happen
                }

            }
        }
    }
}
