package com.easysoft.core.plugin;

import com.easysoft.core.dispatcher.core.freemarker.FreeMarkerParser;
import com.easysoft.framework.component.plugin.IPlugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * 系统设置插件桩
 * @author andy
 *
 */
public class SettingPluginBundle extends AutoRegisterPluginsBundle {
	
 
	protected static final Log loger = LogFactory
			.getLog(SettingPluginBundle.class);



	
	public String getName() {
		return "系统设置插件桩";
	}



	
	public void registerPlugin(IPlugin plugin) {
		super.registerPlugin(plugin);
	}

	public Map<Integer, String> onInputShow(Map<String,Map<String,String>> settings){
		//Map<String,Map<String,String>> settings  = settingService.getSetting();
        Map<Integer, String> htmlMap = new TreeMap<Integer, String>();

		FreeMarkerParser freeMarkerParser =  FreeMarkerParser.getInstance();
        List<IPlugin> plugins = getPlugins();
		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if(plugin instanceof IOnSettingInputShow){
					IOnSettingInputShow event = (IOnSettingInputShow)plugin;
					String groupname = event.getSettingGroupName();
					String pageName = event.onShow();
					
					freeMarkerParser.setClz(event.getClass());
					freeMarkerParser.setPageName(pageName);
					freeMarkerParser.putData(groupname, settings.get(groupname));

                    htmlMap.put(Integer.valueOf(event.getOrder()), freeMarkerParser.proessPageContent());
				}
			}
		}
		return htmlMap;
	}

    public Map<Integer, String> getTabs() {
        Map tabMap = new TreeMap();
        List<IPlugin> plugins = getPlugins();

        if (plugins != null) {
            for (IPlugin plugin : plugins) {
                if ((plugin instanceof IOnSettingInputShow)) {
                    IOnSettingInputShow event = (IOnSettingInputShow) plugin;
                    String name = event.getTabName();
                    tabMap.put(Integer.valueOf(event.getOrder()), name);
                }
            }
        }

        return tabMap;
    }


 
	
}
