package com.easysoft.core.resource;

import com.easysoft.core.ParamSetting;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.model.Site;

import java.util.HashMap;
import java.util.Map;

/**
 * User: andy
 * Date: 13-8-8
 * Time: 上午9:20
 *
 * @since:
 */
public class ResourceStateManager {
    private static int DISPLOY_STATE;
    private static Map<String, String> disployStateMap = new HashMap();

    public static boolean getHaveNewDisploy()
    {
        
        return DISPLOY_STATE == 1;
    }

    public static void setDisplayState(int state)
    {
        
            DISPLOY_STATE = state;
        
    }
}
