package com.easysoft.jeap.core.member.manager;

import com.easysoft.jeap.framework.exception.UncheckedException;

/**
 * @author : andy.huang
 * @since :
 */
public class PermissionException extends UncheckedException {
    public PermissionException(Enum errorCode) {
        super(errorCode);
    }

    public PermissionException(Enum errorCode, Object... para) {
        super(errorCode, para);
    }

    public PermissionException(Enum errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }

    public PermissionException(Enum errorCode, Throwable throwable, Object... para) {
        super(errorCode, throwable, para);
    }
}
