package com.easysoft.framework.exception;
/**
 * The application exception supper class. Extends this class for all application exception.
 * When throw an exception, can take the following three parameters.
 * 1. Error code. Unlike ordinary exception taking string as error, the code must be enum type. The enum value must be
 * marked with ErrorDefinition annotation, specify the error comment (default message).
 * 2. Caused exception. Feature provided by JDK exception mechanism (1.4 and later).
 * 3. Error message parameters. The default message defined in ErrorDefinition can take parameters, fill in dynamic contents.
 * <p/>
 * For example, certain class has two error codes, and ServiceException extends CheckedException.
 * <code>
 * public class MockService {
 * public enum ErrorCodes {
 *
 * @ErrorDefinition(comment = "Input string value ${1} is too long!")
 * ERROR_A,
 * @ErrorDefinition(comment = "Input date value ${1} format is invalid, ${2} is expected!")
 * ERROR_B
 * }
 * <p/>
 * public void serviceFun1(...) throws ServiceException {
 * try {
 * ...
 * } catch (... e) {
 * throw new ServiceException(ERROR_B, e, "07/09/2007", "YYYY-MM-DD");
 * }
 * }
 * }
 * </code>
 * The final error message is "Input date value 07/09/2007 format is invalid, YYYY-MM-DD is expected!"
 * <p/>
 * To support I18N, the default message defined in ErrorDefinition is just a comment. Exception message I18N tool
 * will iterate all error code declarations, and extract corresponding information in ErrorDefinition, then generates
 * a I18N message resource (configuration) file template. Afterwards, we can translate these messages to various languages.
 * Hence, such configuration and translation procedure are totally transparent to developers. For more details, see the
 * I18N tool documents.
 * @author andy
 * @since 1.0
 */
public class CheckedException extends Exception {
    private static final String DEFAULT_MSG = "Exception default comment.\n  Error code: ";
}
