package com.easysoft.core.manager.impl;

import com.easysoft.core.context.EsfContext;
import com.easysoft.framework.ParamSetting;
import com.easysoft.core.common.dao.spring.BaseSupport;
import com.easysoft.framework.db.IDaoSupport;
import com.easysoft.framework.utils.FileUtil;
import com.easysoft.core.manager.IAdminThemeManager;
import com.easysoft.core.model.AdminTheme;
import com.easysoft.core.model.Site;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台主题管理
 * @author andy
 */
@Service("adminThemeManager")
public class AdminThemeManagerImpl extends BaseSupport<AdminTheme> implements IAdminThemeManager {

 
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer add(AdminTheme theme,boolean isCommon) {
		 
		try {
			//this.copy(theme,isCommon);
			this.baseDaoSupport.insert("admintheme", theme);
			return this.baseDaoSupport.getLastId("admintheme");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("安装后台主题出错");
		}
		
	}
	
	public void clean() {
		this.baseDaoSupport.execute("truncate table admintheme");
	}
	
	
	private void copy(AdminTheme theme,boolean isCommon) throws Exception  {
	 
		Site site  = EsfContext.getContext().getCurrentSite();

		//公用模板由common目录复制，非公用由产品目录复制
		String basePath =isCommon? ParamSetting.APP_DATA_STORAGE_PATH: ParamSetting.PRODUCTS_STORAGE_PATH+ "/" + theme.getProductId();
		basePath= basePath +"/adminthemes";
		
		
		String contextPath = EsfContext.getContext().getContextPath();
		//复制图片至静态资源服务器
		String targetPath = ParamSetting.IMG_SERVER_PATH   +contextPath + "/adminthemes/"+ theme.getPath() ;
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/images",targetPath+ "/images");
		FileUtil.copyFile(basePath + "/" + theme.getPath() + "/preview.png", targetPath + "/preview.png");
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/css",targetPath+ "/css");
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/js",targetPath+ "/js");
		
		
		FileUtil.copyFolder(basePath + "/" + theme.getPath() , ParamSetting.ESF_PATH
				+contextPath
				+ "/adminthemes/" + theme.getPath());
		/*
		 * 只考jsp到eop应用服务器中
		
		IOFileFilter txtSuffixFilter = FileFilterUtils.suffixFileFilter(".jsp");
		IOFileFilter txtFiles = FileFilterUtils.andFileFilter(FileFileFilter.FILE, txtSuffixFilter);

		  
		FileUtils.copyDirectory(
		new File(basePath + "/" + theme.getPath() )
		, 
		
		new File(ParamSetting.ESF_PATH
		+ "/user/"
		+ userid
		+ "/"
		+ siteid
		+ "/adminthemes/" + theme.getPath())
		, 
		txtFiles
		);

		 */
	}
	
	
	
	public AdminTheme get(Integer themeid) {
		List<AdminTheme> list= this.baseDaoSupport.queryForList("select * from admintheme where id=?", AdminTheme.class, themeid);
		if(list==null || list.isEmpty()) return null;
		else 
		return list.get(0);
	}

	
	public List<AdminTheme> list() {
		
		return this.baseDaoSupport.queryForList("select * from admintheme ", AdminTheme.class);
	}

	public IDaoSupport<AdminTheme> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<AdminTheme> daoSupport) {
		this.daoSupport = daoSupport;
	}
	



}
