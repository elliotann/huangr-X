package com.easysoft.component.weixin.manager;

import com.easysoft.framework.exception.UncheckedException;

public class WebChatManagerException extends UncheckedException {

	public WebChatManagerException(Enum errorCode, Object... para) {
		super(errorCode, para);
		// TODO Auto-generated constructor stub
	}

	public WebChatManagerException(Enum errorCode, Throwable throwable,
			Object... para) {
		super(errorCode, throwable, para);
		// TODO Auto-generated constructor stub
	}

	public WebChatManagerException(Enum errorCode, Throwable throwable) {
		super(errorCode, throwable);
		// TODO Auto-generated constructor stub
	}

	public WebChatManagerException(Enum errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}



}
