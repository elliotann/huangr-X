package com.easysoft.framework.json.filter;

import com.alibaba.druid.util.StringUtils;
import com.easysoft.framework.json.annotation.JsonInvisible;

import java.lang.reflect.Method;

/**
 * User: andy
 * Date: 14-4-1
 * Time: 下午4:58
 *
 * @since:
 */
public class InvisibleFilter extends AbstractMethodFilter {
    private String guiid;
    public  InvisibleFilter(String guiid){
        this.guiid = guiid;
    }
    @Override
    public boolean apply(Method method) {
        if(StringUtils.isEmpty(guiid)){
            return false;
        }
        if(method.isAnnotationPresent(JsonInvisible.class)){
            JsonInvisible jsonInvisible = method.getAnnotation(JsonInvisible.class);
            String[] values = jsonInvisible.value();
            for(String guValue : values){
                if(guValue.equals(guiid)){
                    return true;
                }
            }
        }
        return false;
    }
}
