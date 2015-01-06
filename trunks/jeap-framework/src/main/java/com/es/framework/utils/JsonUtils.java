package com.es.framework.utils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import com.es.framework.json.filter.InvisibleFilter;



public class JsonUtils {
	

	/**
	 * 把对象转换成json格式字符串
	 * @param bean
	 * @return
	 */
    public static String beanToJson(Object bean){
        JsonConfig config = new JsonConfig();
        config.setJsonPropertyFilter(new InvisibleFilter("List"));
        //config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor(null));
        config.setExcludes(new String[]{"processDefinition","processInstance","task"});
        JSONObject jsonObject = JSONObject.fromObject(bean,config);
        return jsonObject.toString();
    }

    /**
     * json转成bean
     * @param jsonString
     * @param clazz
     * @param map
     * @return
     */
    public static Object jsonToBean(String jsonString,Class clazz,Map<String,Class> map){
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JsonConfig config = new JsonConfig();
        config.setClassMap(map);
        config.setRootClass(clazz);
        config.setJavaPropertyFilter(new InvisibleFilter("LIST"));
        Object bean = JSONObject.toBean(jsonObject,config);
        return bean;
    }

    public static String beanToJsonArray(List beans){
        JsonConfig config = new JsonConfig();
        config.setJavaPropertyFilter(new InvisibleFilter("List"));
        config.setExcludes(new String[]{"form"});

        JSONArray jsonArray = JSONArray.fromObject(beans,config);
        return jsonArray.toString();
    }
    public static Object[] getDTOArray(String jsonString, Class clazz){
        setDataFormat2JAVA();
        JSONArray array = JSONArray.fromObject(jsonString);
        Object[] obj = new Object[array.size()];
        for(int i = 0; i < array.size(); i++){
            JSONObject jsonObject = array.getJSONObject(i);
            obj[i] = JSONObject.toBean(jsonObject, clazz);
        }
        return obj;
    }


	private static void setDataFormat2JAVA() {
		// 设定日期转换格式
		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd",
						"yyyy-MM-dd HH:mm:ss" }));
	}
}
