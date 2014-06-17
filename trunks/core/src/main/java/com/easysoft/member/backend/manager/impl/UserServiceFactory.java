package com.easysoft.member.backend.manager.impl;

import com.easysoft.member.backend.manager.IUserService;

/**
 * 用户服务工厂，返回服务服务
 * @author andy
 *
 */
public final class UserServiceFactory {
	private static IUserService userService;
	public static void set(IUserService _userService){
		 userService =_userService;
	}
	
	public static IUserService getUserService(){
		if(userService!=null)
		 return userService;
		 return new UserServiceImpl();
	}
}



