package com.easysoft.member.plugin;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.member.model.Member;

/**
 * 会员注册事件
 * @author andy
 *
 */
public interface IMemberRegisterEvent {
	
	/**
	 * 会员注册时激发此事件
	 * @param member
	 */
	@Transactional(propagation = Propagation.REQUIRED)  
	public void onRegister(Member member);
	
	
	
}