package com.easysoft.member.backend.dao;
import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.AdminUser;

/**
 * User: andy
 * Date: 14-1-23
 * Time: 上午9:45
 *
 * @since:
 */
public interface IAdminUserDao extends IGenericDao<AdminUser,Integer> {
    public void deleteTable();
    public AdminUser queryUserByName(String username);
}
