package com.easysoft.member.backend.dao.impl;

import com.easysoft.core.common.dao.hibernate.support.GenericDAO;
import com.easysoft.member.backend.dao.IAdminUserDao;
import com.easysoft.member.backend.model.AdminUser;
import org.springframework.stereotype.Repository;

/**
 * User: andy
 * Date: 14-1-23
 * Time: 上午9:45
 *
 * @since:
 */
@Repository
public class AdminUserDao extends GenericDAO<AdminUser> implements IAdminUserDao {
    public AdminUserDao() {
        super(AdminUser.class);
    }
}
