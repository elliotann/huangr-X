package com.easysoft.core.listener;

import com.easysoft.framework.ParamSetting;
import com.easysoft.core.manager.IComponentManager;
import com.easysoft.core.manager.ISiteManager;
import com.easysoft.framework.spring.SpringContextHolder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 负责初始化站点缓存
 * 只有saas版本有效
 *
 * @author andy
 */
public class JEDPContextLoaderListener implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent event) {

    }

    public void contextInitialized(ServletContextEvent event) {
        if ((ParamSetting.INSTALL_LOCK.toUpperCase().equals("YES")) && ("1".equals(ParamSetting.RUNMODE))) {
            IComponentManager componentManager = (IComponentManager) SpringContextHolder.getBean("componentManager");
            componentManager.startComponents();
        }

        if (("2".equals(ParamSetting.RUNMODE)) && (ParamSetting.INSTALL_LOCK.toUpperCase().equals("YES"))) {
            ISiteManager siteManager = (ISiteManager) SpringContextHolder.getBean("siteManager");
            siteManager.getDnsList();
        }
    }

}
