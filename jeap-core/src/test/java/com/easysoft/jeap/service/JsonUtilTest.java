package com.easysoft.jeap.service;

import com.easysoft.jeap.core.member.entity.Menu;
import com.easysoft.jeap.framework.utils.JsonUtil;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangxa on 2014/7/21.
 */
public class JsonUtilTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        JSON json = JSONSerializer.toJSON(list);
        System.out.println(json.toString());


        PropertyFilter filter = new PropertyFilter() {
            @Override
            public boolean apply(Object o, String name, Object o2) {
                if(name.equals("url")){
                    return true;
                }
                return false;
            }
        };
        JsonConfig config = new JsonConfig();
        config.setJsonPropertyFilter(filter);
        JSON json1 = JSONSerializer.toJSON(new Menu(),config);
        System.out.println(json1.toString());

        List<Menu> menus = new ArrayList<Menu>();
        Menu menu = new Menu();
        menus.add(menu);
        Menu menu1 = new Menu();
        menus.add(menu1);
        String jsonStr = JsonUtil.beanToJsonArray(menus);
        System.out.println(jsonStr);
    }
}
