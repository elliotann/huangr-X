package com.es.jeap.core.component.permission;

import com.es.framework.exception.UncheckedException;

public class PermissionException extends UncheckedException {

	public PermissionException(Enum errorCode, Object... para) {
		super(errorCode, para);
		
	}

	public PermissionException(Enum errorCode, Throwable throwable,
			Object... para) {
		super(errorCode, throwable, para);
		
	}

	public PermissionException(Enum errorCode, Throwable throwable) {
		super(errorCode, throwable);
		
	}

	public PermissionException(Enum errorCode) {
		super(errorCode);
		
	}


}
