package com.easysoft.member.backend.manager;

import com.easysoft.framework.exception.UncheckedException;

public class PermissionManagerException extends UncheckedException {

    public PermissionManagerException(Enum errorCode) {
        super(errorCode);
    }

    public PermissionManagerException(Enum errorCode, Object... para) {
        super(errorCode, para);
    }

    public PermissionManagerException(Enum errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }

    public PermissionManagerException(Enum errorCode, Throwable throwable, Object... para) {
        super(errorCode, throwable, para);
    }
}
