package com.easysoft.core.dispatcher.core;

import com.easysoft.core.context.ConnectType;
import com.easysoft.core.dispatcher.core.LocalRequest;
import com.easysoft.core.dispatcher.core.Request;


/**
 * @author andy
 * @version 1.0
 * @created 09-十月-2009 23:08:48
 */
public abstract class RequestFactory {




	public static Request getRequest(int model){
		
		
		Request request = null;
		
		if(model==ConnectType.remote)
			request = new RemoteRequest();
		
		if(model==ConnectType.local)
			request = new LocalRequest();
		
		return request;
	}

}