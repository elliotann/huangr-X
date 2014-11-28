package com.easysoft.member.backend.manager.impl;

import com.easysoft.core.dispatcher.core.EopException;
import com.easysoft.member.backend.manager.IUserService;

/**
 * @author andy
 * @version 1.0
 */
public final class UserUtil {

	private static IUserService userService;
	
	public static void validUser(Integer userid) {
		
		userService = UserServiceFactory.getUserService();
		
		if (!userid.equals(userService.getCurrentUserId())) {
			throw new EopException("非法操作");
		}
		
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
