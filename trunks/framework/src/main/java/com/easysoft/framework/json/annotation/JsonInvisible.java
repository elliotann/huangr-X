package com.easysoft.framework.json.annotation;

import java.lang.annotation.*;

/**
 * bean中属性是否需求json处理
 * User: andy
 * Date: 14-4-1
 * Time: 下午4:27
 *
 * @since:
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonInvisible {
    public String[] value();
}
