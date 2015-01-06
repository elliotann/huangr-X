package com.es.jeap.core.component.permission.manager;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.es.framework.db.pager.PageOption;
import com.es.framework.exception.ErrorCode;
import com.es.jeap.core.component.permission.PermissionException;
import com.es.jeap.core.component.permission.dao.IAdminUserDao;
import com.es.jeap.core.component.permission.entity.AdminUser;
@Service
public class AdminUserManager implements IAdminUserManager {
	private enum AdminUserError{
		@ErrorCode(comment="传入用户信息为空!")
		USER_IS_NULL,
		@ErrorCode(comment="用户名为空")
		USERNAME_IS_NULL,
		@ErrorCode(comment="密码为空")
		PASSWORD_IS_NULL,
		@ErrorCode(comment="邮箱为空")
		EMAIL_IS_NULL
		
	}
	@Autowired
	private IAdminUserDao adminUserDao;
	public void save(AdminUser adminUser) {
		if(adminUser==null) throw new PermissionException(AdminUserError.USER_IS_NULL);
		if(StringUtils.isEmpty(adminUser.getUsername())) throw new PermissionException(AdminUserError.USERNAME_IS_NULL);
		if(StringUtils.isEmpty(adminUser.getPassword())) throw new PermissionException(AdminUserError.PASSWORD_IS_NULL);
		if(StringUtils.isEmpty(adminUser.getEmail())) throw new PermissionException(AdminUserError.EMAIL_IS_NULL);
		adminUserDao.save(adminUser);
	}
	public void update(AdminUser adminuser) {
		
		
	}
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
	public PageOption queryUsers(PageOption pageOption) {
		List<AdminUser> adminUsers = adminUserDao.queryForList();
		pageOption.setData(adminUserDao.queryForList());
		pageOption.setTotalCount(adminUsers.size());
		return pageOption;
	}

}
