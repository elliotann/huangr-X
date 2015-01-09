package com.es.jeap.core.component.permission.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.es.jeap.core.component.permission.dao.IRoleDao;
import com.es.jeap.core.component.permission.entity.Role;
@Service
public class RoleManager implements IRoleManager {
	@Autowired
	private IRoleDao roleDao;
	public List<Role> queryForQry(String roleName) {
		String hql = "from Role r ";
		if(StringUtils.isNotEmpty(roleName)){
			hql += " where r.roleName like '%"+roleName+"%'";
		}
		return roleDao.queryForListByHql(hql);
	}

	public void add(Role role) {
		roleDao.save(role);

	}

	public void update(Role role) {
		roleDao.update(role);

	}

	public void deleteById(Integer id) {
		roleDao.deleteById(id);

	}

	public Role queryById(Integer id) {
		return roleDao.queryById(id);
	}

}
