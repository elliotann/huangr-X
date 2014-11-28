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
    
    public void deleteTable() {

    }

    
    public AdminUser queryUserByName(Map<String, Object> conditions) {
        String hql = " from AdminUser a where a.username='"+conditions.get("username").toString()+"'";
        if(StringUtils.isNotEmpty(conditions.get("userId").toString())&&!"0".equals(conditions.get("userId").toString())){
            hql += " and a.id!='"+conditions.get("userId").toString()+"' ";
        }
        List<AdminUser> adminUserList = this.queryForListByHql(hql);
        if(adminUserList.isEmpty()){
            return null;
        }
        return adminUserList.get(0);

    }


	public AdminUser queryUserByEmail(Map<String, Object> conditions) {
		String hql = " from AdminUser a where a.email='"+conditions.get("email").toString()+"'";
        if(StringUtils.isNotEmpty(conditions.get("userId").toString())&&!"0".equals(conditions.get("userId").toString())){
            hql += " and a.id!='"+conditions.get("userId").toString()+"' ";
        }
        List<AdminUser> adminUserList = this.queryForListByHql(hql);
        if(adminUserList.isEmpty()){
            return null;
        }
        return adminUserList.get(0);
	}


	public List<AdminUser> queryUser4Admin() {
		String hql = " from AdminUser a where a.founder='1'";
		return this.queryForListByHql(hql);
	}
}
