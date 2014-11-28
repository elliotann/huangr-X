package com.easysoft.member.backend.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.RoleAuth;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class RoleAuthDao extends HibernateGenericDao<RoleAuth,Integer> implements IRoleAuthDao {
    
    public List<RoleAuth> queryAuthByRoleId(Integer roleId) {
    	String hql = "from RoleAuth ra where ra.roleId=:roleId";
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("roleId", roleId);
    	return this.queryForHQL(hql, params);
    }
    public RoleAuth queryAuthByRoleIdAndFun(Map<String, Object> conditions) {
    	String hql = "from RoleAuth ra where ra.roleId=:roleId and ra.funId=:funId";
    	List<RoleAuth> roleAuths = this.queryForHQL(hql, conditions);
    	if(roleAuths.isEmpty()){
    		return null;
    	}
    	return roleAuths.get(0);
    }
    
    public void deleteByRoleId(int roleId) {
    	String sql = "delete from t_role_auth where role_id="+roleId;
    	this.excuteBySql(sql);
    }
}
