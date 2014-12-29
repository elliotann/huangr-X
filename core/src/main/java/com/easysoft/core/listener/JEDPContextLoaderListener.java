package com.easysoft.core.listener;

import com.easysoft.core.ParamSetting;
import com.easysoft.core.manager.IComponentManager;
import com.easysoft.core.manager.ISiteManager;
import com.easysoft.framework.utils.SpringContextHolder;

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
        if ((ParamSetting.INSTALL_LOCK.toUpperCase().equals("YES")) ) {
            IComponentManager componentManager = (IComponentManager) SpringContextHolder.getBean("componentManager");
            componentManager.startComponents();
        }

    }

}
