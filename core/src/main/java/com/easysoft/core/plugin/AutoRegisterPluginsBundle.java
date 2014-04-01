package com.easysoft.core.plugin;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.model.Site;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.plugin.IPlugin;
import com.easysoft.framework.plugin.IPluginBundle;
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
        if ("2".equals(ParamSetting.RUNMODE)) {
            registerPlugin2(plugin);
        }

        if ("1".equals(ParamSetting.RUNMODE))
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
        if ("2".equals(ParamSetting.RUNMODE)) {
            if (this.saasPlugins == null) {
                return new ArrayList();
            }

            String key = getKey();
            List pluginList = (List) this.saasPlugins.get(key);
            if (pluginList == null) {
                return new ArrayList();
            }
            return pluginList;
        }

        return this.plugins;
    }

    private String getKey() {
        Site site = EsfContext.getContext().getCurrentSite();
        int userid = site.getUserid().intValue();
        int siteid = site.getId().intValue();
        String key = userid + "_" + siteid;

        return key;
    }
    public synchronized void unRegisterPlugin(IPlugin _plugin)
/*     */   {
/*  87 */     if ("2".equals(ParamSetting.RUNMODE))
/*     */     {
/*  89 */       if (this.saasPlugins == null) {
/*  90 */         return;
/*     */       }
/*     */
/*  93 */       String key = getKey();
/*  94 */       List pluginList = (List)this.saasPlugins.get(key);
/*  95 */       if (pluginList == null) {
/*  96 */         return;
/*     */       }
/*  98 */       pluginList.remove(_plugin);
/*     */     }
/* 103 */     else if (this.plugins != null) {
/* 104 */       this.plugins.remove(_plugin);
/*     */     }
/*     */   }

}

