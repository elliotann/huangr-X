package com.easysoft.core.json;

import com.easysoft.core.model.Birthday;
import com.easysoft.core.model.FormEntity;
import com.easysoft.core.model.FormField;
import com.easysoft.framework.utils.JsonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * User: andy
 * Date: 14-4-1
 * Time: 下午5:23
 *
 * @since:
 */
public class JsonTest {
    private JSONArray jsonArray = null;
    private JSONObject jsonObject = null;
    private FormEntity bean = null;

    @Before
    public void init() {
        jsonArray = new JSONArray();
        jsonObject = new JSONObject();
        bean = new FormEntity();
        bean.setId(1);
        bean.setTableName("cms_provide_loan_info");

        bean.setTableTitle("借款信息");
        FormField field1 = new FormField();
        field1.setId(1);
        List<FormField> fields = new ArrayList<FormField>();
        fields.add(field1);
        bean.setFields(fields);
    }

    @After
    public void destory() {
        jsonArray = null;
        jsonObject = null;
        bean = null;
        System.gc();
    }

    public final void fail(String string) {
        System.out.println(string);
    }

    public final void failRed(String string) {
        System.err.println(string);
    }

    @Test
    public void bean2json() {
        /*fail("==============Java Bean >>> JSON Object==================");
        fail(JSONObject.fromObject(bean).toString());
        fail("==============Java Bean >>> JSON Array==================");
        fail(JSONArray.fromObject(bean).toString());//array会在最外层套上[]
        fail("==============Java Bean >>> JSON Object ==================");
        fail(JSONSerializer.toJSON(bean).toString());
        fail("========================JsonConfig========================");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Birthday.class, new JsonValueProcessor() {
            public Object processArrayValue(Object value, JsonConfig jsonConfig) {
                if (value == null) {
                    return new Date();
                }
                return value;
            }

            public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
                fail("key:" + key);
                return value + "##修改过的日期";
            }
        });
        jsonObject = JSONObject.fromObject(bean, jsonConfig);
        fail(jsonObject.toString());

        FormEntity student = (FormEntity) JSONObject.toBean(jsonObject, FormEntity.class);
        fail(jsonObject.getString("birthday"));
        fail(student.toString());

        fail("#####################JsonPropertyFilter############################");
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                fail(source + "%%%" + name + "--" + value);            //忽略birthday属性
                if (value != null && Birthday.class.isAssignableFrom(value.getClass())) {
                    return true;
                }
                return false;
            }
        });

        fail(JSONObject.fromObject(bean, jsonConfig).toString());
        fail("#################JavaPropertyFilter##################");
        jsonConfig.setRootClass(FormEntity.class);

        jsonConfig.setJavaPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                fail(name + "@" + value + "#" + source);
                if ("id".equals(name) || "email".equals(name)) {
                    value = name + "@@";
                    return true;
                }
                return false;
            }
        });
        //jsonObject = JSONObject.fromObject(bean, jsonConfig);
        // //student = (Student) JSONObject.toBean(jsonObject, Student.class);
        // //fail(student.toString());
         student = (FormEntity) JSONObject.toBean(jsonObject, jsonConfig);
         fail("Student:" + student.toString());*/
        fail(JsonUtils.beanToJson(bean));

        String str = JsonUtils.beanToJson(bean);
        Map<String,Class> map = new HashMap<String,Class>();
        map.put("fields",FormField.class);
        FormEntity bean1 = (FormEntity)JsonUtils.jsonToBean(str,FormEntity.class,map);
        System.out.println(bean1);
    }
}
