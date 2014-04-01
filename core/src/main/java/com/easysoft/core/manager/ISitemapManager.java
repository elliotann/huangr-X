package com.easysoft.core.manager;

import com.easysoft.core.model.SiteMapUrl;

/**
 * Sitemap接口
 * 
 * @author andy<br/>
 */
public interface ISitemapManager {

	/**
	 * 取得sitemap的文字串(xml格式)
	 * 
	 * @return
	 */
	public String getsitemap();

	/**
	 * 向sitemap中加入一个url
	 * 
	 * @param url
	 */
	public void addUrl(SiteMapUrl url);

	/**
	 * 修改sitemap中的url
	 * 
	 * @param loc
	 */
	public void editUrl(String loc, Long lastmod);
	
	
	
	public int delete(String loc);
	
	public void clean();

	

}
