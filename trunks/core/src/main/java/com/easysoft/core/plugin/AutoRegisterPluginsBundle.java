package com.easysoft.core.plugin;

import com.easysoft.core.ParamSetting;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.model.Site;
import com.easysoft.framework.component.plugin.IPlugin;
import com.easysoft.framework.component.plugin.IPluginBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动注册插件桩
 * 
 * @author andy
 * 
 */
public abstract class AutoRegisterPluginsBundle implements IPluginBundle {
	protected static final Log loger = LogFactory
			.getLog(AutoRegisterPluginsBundle.class);

	protected List<IPlugin> plugins;
	private Map<String, List<IPlugin>> saasPlugins;

	public void registerPlugin(IPlugin plugin) {

		registerPlugin1(plugin);

	}

	private void registerPlugin1(IPlugin plugin) {
		if (this.plugins == null) {
			this.plugins = new ArrayList();
		}

		if (!this.plugins.contains(plugin)) {
			this.plugins.add(plugin);
		}
		if (loger.isDebugEnabled())
			loger.debug("为插件桩" + getName() + "注册插件：" + plugin.getClass());
	}

	private void registerPlugin2(IPlugin plugin) {
		if (this.saasPlugins == null) {
			this.saasPlugins = new HashMap();
		}

		String key = getKey();

		List pluginList = (List) this.saasPlugins.get(key);

		if (pluginList == null) {
			pluginList = new ArrayList();
			this.saasPlugins.put(key, pluginList);
		}
		if (!pluginList.contains(plugin))
			pluginList.add(plugin);
	}

	public abstract String getName();

	public synchronized List<IPlugin> getPlugins() {

		return this.plugins;
	}

	private String getKey() {
		Site site = EsfContext.getContext().getCurrentSite();
		int userid = site.getUserid().intValue();
		int siteid = site.getId().intValue();
		String key = userid + "_" + siteid;

		return key;
	}

	public synchronized void unRegisterPlugin(IPlugin _plugin) {

		if (this.plugins != null) {
			this.plugins.remove(_plugin);
		}
	}

}
