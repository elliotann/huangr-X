package com.easysoft.jeap.framework.utils;


import com.easysoft.jeap.framework.utils.json.DateJsonValueProcessor;
import com.easysoft.jeap.framework.utils.json.InvisibleFilter;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.*;
import net.sf.json.util.JSONUtils;

import java.util.*;
import java.util.List;


public class JsonUtil {
	public static Object getDTO(String jsonString, Class clazz,Class childrenClazz,String childName) {
		JSONObject jsonObject = null;
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put(childName, childrenClazz); //指定Classes的students字段的内部类型
		try {
			setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz,classMap);
	}

	private static void setDataFormat2JAVA() {
		// 设定日期转换格式
		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd",
						"yyyy-MM-dd HH:mm:ss" }));
	}

    public static String beanToJson(Object bean){

        JsonConfig config = new JsonConfig();

        config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor(null));
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
        config.setJsonPropertyFilter(new InvisibleFilter("List"));
        config.setExcludes(new String[]{"form"});

        JSONArray jsonArray = JSONArray.fromObject(beans,config);
        return jsonArray.toString();
    }



}
