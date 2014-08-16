package com.easysoft.core.common.dao.spring;

import com.easysoft.core.context.EsfContext;
import com.easysoft.framework.db.IDBRouter;
import com.easysoft.framework.db.IDaoSupport;
import com.easysoft.framework.exception.ErrorCode;
import com.easysoft.member.backend.manager.PermissionManagerException;
import org.apache.log4j.Logger;


public abstract class BaseSupport<T> {
    public enum BaseSupportError{
        @ErrorCode(comment = "用户为空!")
        USER_ID_NULL,
        @ErrorCode(comment = "登录用户失效!")
        LOGIN_USER_VALID
    }
	protected final Logger logger = Logger.getLogger(getClass());
		
	private IDBRouter baseDBRouter;
	protected IDaoSupport<T> baseDaoSupport;
	protected IDaoSupport<T> daoSupport;   

	/**
	 * 获取表名
	 * @return
	 */
	protected String getTableName(String moude){
		return baseDBRouter.getTableName(moude);
		
	}

	/**
	 * 检测操作的“属主”合法性
	 * @param userid
	 */
	protected void checkIsOwner( final Integer userid){
		if(userid==null){
			throw new PermissionManagerException(BaseSupportError.USER_ID_NULL);
		}
		
		Integer suserid = EsfContext.getContext().getCurrentSite().getUserid();
		
		if(suserid.intValue()!=userid.intValue()){
			throw new PermissionManagerException(BaseSupportError.LOGIN_USER_VALID);
		}
	}

	public IDaoSupport<T> getBaseDaoSupport() {
		return baseDaoSupport;
	}

	public void setBaseDaoSupport(IDaoSupport<T> baseDaoSupport) {
		this.baseDaoSupport = baseDaoSupport;
	}
	
	public void setDaoSupport(IDaoSupport<T> daoSupport) {
		this.daoSupport = daoSupport;
	}

	public IDBRouter getBaseDBRouter() {
		return baseDBRouter;
	}

	public void setBaseDBRouter(IDBRouter baseDBRouter) {
		this.baseDBRouter = baseDBRouter;
	}
}
