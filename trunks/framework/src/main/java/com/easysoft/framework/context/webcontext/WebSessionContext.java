package com.easysoft.framework.context.webcontext;

import javax.servlet.http.HttpSession;
import java.util.Set;

public interface WebSessionContext<T> {

	public static String sessionAttributeKey = "EOPSessionKey";

	public  HttpSession getSession();

	public  void setSession(HttpSession session);

	public  void invalidateSession();

	public  void setAttribute(String name, T value);

	public  T getAttribute(String name);

	public  Set<T> getAttributeNames();

	public  void removeAttribute(String name);
	
	public  void destory();

}