package com.easysoft.member.backend.dao;
import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.AdminUser;

import java.util.Map;

/**
 * User: andy
 * Date: 14-1-23
 * Time: 上午9:45
 *
 * @since:
 */
public interface IAdminUserDao extends IGenericDao<AdminUser,Integer> {
    public void deleteTable();

    /**
     * 根据用户名查询用户
     * @param username
     * @param userId 修改时用
     * @return
     */
    public AdminUser queryUserByName(Map<String,Object> conditions);
    /**
     * 根据email查询用户
     * @param conditions
     * @return
     */
    public AdminUser queryUserByEmail(Map<String,Object> conditions);
}
