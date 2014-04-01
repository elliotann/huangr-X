package com.easysoft.member.plugin;

import com.easysoft.member.model.Member;

/**
 * 会员注销事件
 * @author andy
 *
 */
public interface IMemberLogoutEvent {
	 
	public void onLogout(Member member);
	
	
}
