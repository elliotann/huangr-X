package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class UserRoleDao extends HibernateGenericDao<UserRole,Integer> implements IUserRoleDao {
    
    public List<UserRole> queryRolesByUserId(Integer userid) {
        String hql = "from UserRole ur where ur.adminUser.id="+userid;
        return this.queryForListByHql(hql);
    }

	public void delUserRoleByRoleId(Integer roleId) {
		String sql = "delete from t_user_role  where roleid="+roleId;
		this.excuteBySql(sql);
		
	}
}
