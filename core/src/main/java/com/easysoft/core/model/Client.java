package com.easysoft.core.model;

import com.easysoft.member.backend.model.AdminUser;
import com.easysoft.member.backend.model.Menu;

import java.util.Map;

/**
 * 在线用户对象
 * @version 1.0
 */
public class Client implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private AdminUser user;

	private Map<String, Menu> functions;
	/**
	 * 用户IP
	 */
	private String ip;
	/**
	 *登录时间
	 */
	private java.util.Date logindatetime;

    public AdminUser getUser() {
        return user;
    }

    public void setUser(AdminUser user) {
        this.user = user;
    }

    public Map<String, Menu> getFunctions() {
		return functions;
	}

	public void setFunctions(Map<String, Menu> functions) {
		this.functions = functions;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public java.util.Date getLogindatetime() {
		return logindatetime;
	}

	public void setLogindatetime(java.util.Date logindatetime) {
		this.logindatetime = logindatetime;
	}


}
