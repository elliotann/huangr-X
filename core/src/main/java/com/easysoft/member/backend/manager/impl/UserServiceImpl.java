package com.easysoft.member.backend.manager.impl;

import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.context.webcontext.WebSessionContext;
import com.easysoft.member.backend.manager.IUserService;
import com.easysoft.member.backend.manager.UserContext;
import com.easysoft.member.backend.model.AdminUser;

import org.apache.log4j.Logger;

public final class UserServiceImpl implements IUserService {
	private UserContext userContext;
	
	protected static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	public UserServiceImpl() {
//		if(logger.isDebugEnabled()){
//			logger.debug("create userservice impl ");
//		}
		WebSessionContext<UserContext> webSessionContext = ThreadContextHolder
				.getSessionContext();
		userContext = webSessionContext.getAttribute(UserContext.CONTEXT_KEY);
		
//		if(logger.isDebugEnabled()){
//			logger.debug("get  userContext is "+ userContext);
//		}
	}

	
	public Integer getCurrentSiteId() {
		if (isUserLoggedIn()) {
			return userContext.getSiteid();
		}
		throw new IllegalStateException("The current user is not logged in.");
	}

	
	public Integer getCurrentUserId() {
		if (isUserLoggedIn()) {
			return userContext.getUserid();
		}
		throw new IllegalStateException("The current user is not logged in.");
	}

	
	public boolean isUserLoggedIn() {
		if (userContext == null)
			return false;
		else
			return true;
	}

	
	public Integer getCurrentManagerId() {
		if (isUserLoggedIn()) {
			return userContext.getManagerid();
		}
		throw new IllegalStateException("The current user is not logged in.");
	}


	@Override
	public AdminUser getCurrentUser() {
		WebSessionContext<AdminUser>  sessonContext = ThreadContextHolder
				.getSessionContext();			
				return  sessonContext.getAttribute("admin_user_key");
	}

	
	
}
