package com.easysoft.framework.exception;

import java.io.Serializable;

/**
 * The error details model, contains error message, code, etc.
 * An exception can contains a list of error records, for example, validation exception may contains
 * multiple records caused by a serial of validation errors.
 * @author andy
 * @since 1.0
 */
public class ErrorRecord implements Serializable {
}
