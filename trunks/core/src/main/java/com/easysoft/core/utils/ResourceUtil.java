package com.easysoft.core.utils;

/**
 * User: andy
 * Date: 14-3-6
 * Time: 上午11:26
 *
 * @since:
 */
public class ResourceUtil {
    /**
     * 获取数据库类型
     *
     * @return
     * @throws Exception
     */
    public static final String getJdbcUrl() {
        return DBTypeUtil.getDBType().toLowerCase();
    }

}
