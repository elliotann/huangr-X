package com.easysoft.core.plugin;

/**
 * 设置页面显示事件
 * @author andy
 */
public interface IOnSettingInputShow {
	
	/**
	 * 响应输入界面显示事件
	 * @return 对应插件输入界面名称
	 */
	public String onShow();
	
	
	
	/**
	 * 得到插件设置的组名
	 * @return
	 */
	public String getSettingGroupName();

    public int getOrder();

    public String getTabName();
}
