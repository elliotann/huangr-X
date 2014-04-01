package com.easysoft.framework.db;

import java.util.HashMap;
import java.util.Map;

/**
 * User: andy
 * Date: 13-7-23
 * Time: 下午6:53
 *
 * @since:
 */
public class DynamicField {
    private Map<String, Object> fields;

    public DynamicField() {
        this.fields = new HashMap();
    }

    public void addField(String name, Object value) {
        this.fields.put(name, value);
    }

    @NotDbField
    public Map<String, Object> getFields() {
        return this.fields;
    }
}
