package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.AdminUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class AdminUserDao extends HibernateGenericDao<AdminUser,Integer> implements IAdminUserDao {
    @Override
    public void deleteTable() {

    }

    @Override
    public AdminUser queryUserByName(Map<String, Object> conditions) {
        String hql = " from AdminUser a where a.username="+conditions.get("username");
        if(StringUtils.isNotEmpty(conditions.get("userId").toString())){
            hql += " and a.id!=? ";
        }
        List<AdminUser> adminUserList = this.queryForListByHql(hql);
        if(adminUserList.isEmpty()){
            return null;
        }
        return adminUserList.get(0);

    }
}
