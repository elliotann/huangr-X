package com.easysoft.core.manager.impl;

import com.easysoft.core.common.dao.spring.BaseSupport;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.manager.IThemeManager;
import com.easysoft.core.model.Theme;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("themeManager")
public class ThemeManagerImpl extends BaseSupport<Theme> implements IThemeManager {
 

	public void clean() {
		this.baseDaoSupport.execute("truncate table theme");
	}
	public Theme getTheme(Integer themeid) {
		//System.out.println(themeid);
		return this.baseDaoSupport.queryForObject("select * from theme where id=?", Theme.class, themeid);
	}

	
	public List<Theme> list() {
		if(EsfContext.getContext().getCurrentSite().getMulti_site()==0){
			return this.baseDaoSupport.queryForList("select * from theme where siteid = 0",Theme.class);
		}else{
			return this.baseDaoSupport.queryForList("select * from theme where siteid = ?",Theme.class, EsfContext.getContext().getCurrentChildSite().getSiteid());
		}
		
	}
	
	/* 
	 * 取得主站的theme列表
	 * (non-Javadoc)
	 * @see com.elliot.eop.core.resource.IThemeManager#getMainThemeList()
	 */
	public List<Theme> list(int siteid) {
		return this.baseDaoSupport.queryForList("select * from theme where siteid = 0",Theme.class);
	}

	public void addBlank(Theme theme){
		try {
	 
			//公用模板由common目录复制，非公用由产品目录复制
			String basePath =  ParamSetting.APP_DATA_STORAGE_PATH;
			basePath =basePath + "/themes";
			
			//复制资源到静态资源服务器
			theme.setSiteid(0);
			String contextPath = EsfContext.getContext().getContextPath();
			String targetPath  = ParamSetting.IMG_SERVER_PATH+ contextPath+ "/themes/" + theme.getPath();
			FileUtil.copyFolder(basePath + "/blank/images", targetPath + "/images");
			FileUtil.copyFile(basePath + "/blank/preview.png",targetPath + "/preview.png");
			FileUtil.copyFolder(basePath + "/blank/css",targetPath + "/css");
			FileUtil.copyFolder(basePath + "/blank/js",targetPath+ "/js");
			FileUtil.copyFolder(basePath + "/blank", ParamSetting.ESF_PATH
					+contextPath
					+ "/themes/" + theme.getPath());
			this.baseDaoSupport.insert("theme", theme);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建主题出错");
		}
	}
	
	public Integer add(Theme theme,boolean isCommon) {
		try {
			this.copy(theme,isCommon);
		//	System.out.println("安装"+ theme.getThemename());
			this.baseDaoSupport.insert("theme", theme);
			return this.baseDaoSupport.getLastId("theme");
		} catch (Exception e) {
			 
			e.printStackTrace();
			throw new RuntimeException("安装主题出错");
		}
		
	}

	
	private  void copy( Theme theme ,boolean isCommon) throws Exception {
		//公用模板由common目录复制，非公用由产品目录复制
		String basePath = isCommon? ParamSetting.APP_DATA_STORAGE_PATH: ParamSetting.PRODUCTS_STORAGE_PATH+"/" + theme.getProductId() ;
		basePath =basePath + "/themes";
		
		//复制资源到静态资源服务器
		String contextPath = EsfContext.getContext().getContextPath();
		String targetPath  = ParamSetting.IMG_SERVER_PATH+ contextPath+ "/themes/" + theme.getPath();
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/images",targetPath + "/images");
		FileUtil.copyFile(basePath + "/" + theme.getPath() + "/preview.png",targetPath + "/preview.png");
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/css",targetPath + "/css");
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/js",targetPath+ "/js");

		
		FileUtil.copyFolder(basePath + "/" + theme.getPath(), ParamSetting.ESF_PATH
				+contextPath
				+ "/themes/" + theme.getPath());
		/*
		 * 只考jsp到eop应用服务器中
		 
		IOFileFilter txtSuffixFilter = FileFilterUtils.suffixFileFilter(".jsp");
		IOFileFilter txtFiles = FileFilterUtils.andFileFilter(FileFileFilter.FILE, txtSuffixFilter);

		FileUtils.copyDirectory(
				new File(basePath + "/" + theme.getPath() )
				, 
				
				new File(ParamSetting.ESF_PATH
				+ "/user/"
				+userid
				+ "/"
				+siteid
				+ "/themes/" + theme.getPath())
				, 
				txtFiles
				);
		
		
		FileUtil.copyFolder(basePath + "/" + theme.getPath(), StringUtil
				.getRootPath()
				+ "/user/"
				+userid
				+ "/"
				+siteid
				+ "/themes/" + theme.getPath());
				*/
	}
}
