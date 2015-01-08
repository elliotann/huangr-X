package com.es.jeap.core.component.permission.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LikeExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.es.framework.db.pager.PageOption;
import com.es.framework.exception.ErrorCode;
import com.es.framework.utils.EncryptUtils;
import com.es.jeap.core.component.log.annotation.BusinessLog;
import com.es.jeap.core.component.log.annotation.State;
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
	@BusinessLog(state = State.VALID,success = "增加用户")
	public void save(AdminUser adminUser) {
		if(adminUser==null) throw new PermissionException(AdminUserError.USER_IS_NULL);
		if(StringUtils.isEmpty(adminUser.getUsername())) throw new PermissionException(AdminUserError.USERNAME_IS_NULL);
		if(StringUtils.isEmpty(adminUser.getEmail())) throw new PermissionException(AdminUserError.EMAIL_IS_NULL);
		adminUser.setPassword(EncryptUtils.md5("88888888"));
		adminUser.setCreateBy("admin");//todo:后续替换成登录的用户名
		adminUserDao.save(adminUser);
	}
	public void update(AdminUser adminuser) {
		
		adminUserDao.update(adminuser);
	}
	public void deleteById(Integer id) {
		adminUserDao.deleteById(id);
		
	}
	public PageOption queryUsers(PageOption pageOption) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		if(pageOption.getSearchConditions().get("username")!=null&&!"".equals(pageOption.getSearchConditions().get("username").toString())){
			criterions.add(Restrictions.like("username", pageOption.getSearchConditions().get("username")));
		}
		
		adminUserDao.queryForPage(pageOption,criterions);
		
		return pageOption;
	}
	public AdminUser queryUserByName(String username,Integer id) {
		return adminUserDao.queryUserByName(username,id);
	}
	public AdminUser queryUserByEmail(String email,Integer id) {
		return adminUserDao.queryUserByEmail(email,id);
	}
	public AdminUser queryUserById(Integer id) {
		return adminUserDao.queryById(id);
	}

}
