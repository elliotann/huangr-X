package com.easysoft.core.manager;

import com.easysoft.core.model.WidgetBundle;

import java.util.List;

/**
 * 挂件管理
 * @author andy
 * @version 1.0
 */
public interface IWidgetBundleManager {

	
	public void add(WidgetBundle widgetBundle);
	public List<WidgetBundle> getWidgetbundleList();
	public WidgetBundle getWidgetBundle(String widgetType);
}
