package com.easysoft.core.component.context;


import com.easysoft.core.ParamSetting;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.model.Site;

import java.util.HashMap;
import java.util.Map;

public class WidgetContext
{
    private static Map<String, Boolean> widgetState;
    private static Map<String, Map<String, Boolean>> saasWidgetState;

    public static void putWidgetState(String widgetId, Boolean state)
    {
       
            widgetState.put(widgetId, state);
   
    }

    public static Boolean getWidgetState(String widgetId)
    {
        Boolean state = (Boolean)widgetState.get(widgetId);
        if (state == null) return Boolean.valueOf(true);
        return state;
    }

    private static String getKey(){
        Site site = EsfContext.getContext().getCurrentSite();
        int userid = site.getUserid().intValue();
        int siteid = site.getId().intValue();
        String key = userid + "_" + siteid;

        return key;
    }

    static
    {
        
       widgetState = new HashMap();
    }
}