package com.easysoft.framework.component;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.easysoft.framework.component.context.ComponentContext;
import com.easysoft.framework.component.plugin.AutoRegisterPlugin;
import com.easysoft.framework.component.plugin.IPluginBundle;

@Component
public class ComponentLoader implements BeanPostProcessor {
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        AutoRegisterPlugin plugin = null;
        if ((bean instanceof AutoRegisterPlugin)) {
            plugin = (AutoRegisterPlugin) bean;
            if (plugin.getBundleList() != null) {
                List<IPluginBundle> pluginBundelList = plugin.getBundleList();
                for (IPluginBundle bundle : pluginBundelList) {
                    bundle.registerPlugin(plugin);
                }
            }
        }

        if ((bean instanceof IComponent)) {
            IComponent component = (IComponent) bean;
            ComponentView componentView = new ComponentView();
            componentView.setComponent(component);
            componentView.setComponentid(beanName);
            ComponentContext.registerComponent(componentView);
        }
        return bean;

    }

}
