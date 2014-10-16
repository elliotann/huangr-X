package com.easysoft.member.backend.manager.impl;

import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.log.annotation.BusinessLog;
import com.easysoft.core.log.annotation.State;
import com.easysoft.core.log.context.BnLogContext;
import com.easysoft.core.model.MultiSite;
import com.easysoft.core.model.Site;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.context.webcontext.WebSessionContext;
import com.easysoft.framework.db.PageOption;
import com.easysoft.framework.utils.DateUtil;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.dao.IAdminUserDao;
import com.easysoft.member.backend.manager.IAdminUserManager;
import com.easysoft.member.backend.manager.IPermissionManager;
import com.easysoft.member.backend.manager.UserContext;
import com.easysoft.member.backend.model.AdminUser;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 管理员管理实现
 * @author andy
 */
@Service("adminUserManager")
public class AdminUserManagerImpl  implements IAdminUserManager {
    @Autowired
    private IAdminUserDao adminUserDao;
    @Autowired
	private IPermissionManager permissionManager;

    private IdentityService identityService;
	
	public void clean() {
        adminUserDao.deleteTable();
	}
	@Transactional(propagation = Propagation.REQUIRED)
    @BusinessLog(state = State.VALID,success = "增加用户")
	public Integer add(AdminUser adminUser) {
		adminUser.setPassword( StringUtil.md5(adminUser.getPassword()) );
		//添加管理员
        adminUserDao.save(adminUser);

        //增加审批用户
        saveToApproUser(adminUser,true);
		//给用户赋予角色
		permissionManager.giveRolesToUser(adminUser.getId(), adminUser.getRoleids());
		return adminUser.getId();
	}

    /**
     * 更新审批用户
     * @param adminUser
     * @param toApprove
     */

    private void saveToApproUser(AdminUser adminUser,boolean toApprove){
        if(toApprove){
            UserQuery userQuery = identityService.createUserQuery();
            List<User> approveUserList = userQuery.userId(adminUser.getUsername()).list();
            if(approveUserList.size()>1){
                throw new RuntimeException("发现重复用户!");
            }else if(approveUserList.size()==1){
                //更新操作
            }else{
                //新增操作
                User approveUser = identityService.newUser(adminUser.getUsername());
                contructApprUser(adminUser,approveUser);
                identityService.saveUser(approveUser);
                addMemberShip(adminUser.getUsername(),adminUser.getRoleids());
            }
        }
    }

    private void addMemberShip(String username,int[] roles){
        if(roles==null) return;
        for(int i=0;i<roles.length;i++){
            identityService.createMembership(username,roles[i]+"");
        }
    }

    /**
     * 构建审批用户
     * @return
     */
    private void contructApprUser(AdminUser adminUser,User approveUser){
        approveUser.setId(adminUser.getUsername());
        approveUser.setPassword(adminUser.getPassword());
        approveUser.setFirstName(adminUser.getRealname());
    }


	/**
	 * 为某个站点添加管理员
	 * @param userid
	 * @param siteid
	 * @param adminUser
	 * @return 添加的管理员id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer add(int userid, int siteid, AdminUser adminUser) {
		adminUser.setState(1);
        adminUserDao.save(adminUser);
		return adminUser.getId();
	}
	
	public int checkLast() {
		int count =0;// this.baseDaoSupport.queryForInt("select count(0) from adminuser");
		return count;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Integer id) {
		//如果只有一个管理员，则抛出异常
		if(this.checkLast()==1){
			throw new RuntimeException("必须最少保留一个管理员");
		}
		
		//清除用户角色
		permissionManager.cleanUserRoles(id);
		
		//删除用户基本信息
        adminUserDao.deleteById(id);

	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void edit(AdminUser adminUser) {
		
		//给用户赋予角色
		permissionManager.giveRolesToUser(adminUser.getId(), adminUser.getRoleids());
		
		//修改用户基本信息
		if( !StringUtil.isEmpty( adminUser.getPassword() ))
		adminUser.setPassword( StringUtil.md5(adminUser.getPassword()) );
        adminUserDao.update(adminUser);
	}

	public AdminUser get(Integer id) {
		return adminUserDao.queryById(id);
	}

	public List list() {
		return adminUserDao.queryForList();
	}
	

	public List<Map> list(Integer userid, Integer siteid) {
		String sql  ="select * from es_adminuser_"+ userid +"_"+ siteid ;
		return null;//this.daoSupport.queryForList(sql);
	}
	
	
	/**
	 * 管理员登录
	 * @param username
	 * @param password 未经过md5加密的密码
	 * @return 登录成功返回管理员
	 * @throws RuntimeException 当登录失败时抛出此异常，登录失败的原因可通过getMessage()方法获取
	 */
	public int login(String username, String password) {
		return this.loginBySys(username, StringUtil.md5(password));
	}

	
	/**
	 * 系统登录
	 * @param username
	 * @param password 此处为未经过md5加密的密码
	 * @return 返回登录成功的用户id
	 * @throws RuntimeException 登录失败抛出此异常，登录失败原因可通过getMessage()方法获取
	 */
	public int loginBySys(String username, String password) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("username",username);
        params.put("userId",0);
        AdminUser user =adminUserDao.queryUserByName(params);
		if(user == null){
            throw new RuntimeException("此用户不存在");
        }
		if(! password.equals(  user.getPassword() )){
			throw new RuntimeException("密码错误");
		}
		if(user.getState()==0){
			throw new RuntimeException("此用户已经被禁用");
		} 
		
		Site site  = EsfContext.getContext().getCurrentSite();
		
		// 开启多站点功能
		if(site.getMulti_site()==1){
			if(user.getFounder()!=1){ //非超级管理员检测是否是站点管理员
				 MultiSite childSite = EsfContext.getContext().getCurrentChildSite();
				 if(user.getSiteid()==null || childSite.getSiteid()!=user.getSiteid()){
					 throw new RuntimeException("非此站点管理员");
				 }
			}
		}
		
		//更新月登录次数和此站点的最后登录时间
		int logincount = site.getLogincount();
		long lastlogin  =( (long)site.getLastlogin() )* 1000;
		Date today = new Date();
		if(DateUtil.toString(new Date(lastlogin), "yyyy-MM").equals(DateUtil.toString(today, "yyyy-MM"))){//与上次登录在同一月内
			logincount++;
		}else{
			logincount = 1;
		}
		
		site.setLogincount(logincount);
		site.setLastlogin(DateUtil.getDatelineLong());
		//this.daoSupport.execute("update eop_site set logincount=?, lastlogin=? where id=?", logincount,site.getLastlogin(),site.getId());
		
		//记录session信息
		WebSessionContext sessonContext = ThreadContextHolder
		.getSessionContext();			
		UserContext userContext = new UserContext(site.getUserid(),
				site.getId(), user.getId());
		sessonContext.setAttribute(UserContext.CONTEXT_KEY, userContext);
		sessonContext.setAttribute("admin_user_key", user);

        return user.getId();
	}	
	
	
	public AdminUser getCurrentUser(){
		WebSessionContext<AdminUser>  sessonContext = ThreadContextHolder
		.getSessionContext();			
		return  sessonContext.getAttribute("admin_user_key");
	}
	
	
	public IPermissionManager getPermissionManager() {
		return permissionManager;
	}

	public void setPermissionManager(IPermissionManager permissionManager) {
		this.permissionManager = permissionManager;
	}

    public void setAdminUserDao(IAdminUserDao adminUserDao) {
        this.adminUserDao = adminUserDao;
    }
    @Autowired
    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Override
    public AdminUser getAdminUserByName(String name, Integer userId) {
        if(userId==null) userId=0;
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("username",name);
        params.put("userId",userId);
        return adminUserDao.queryUserByName(params);

    }

    @Override
    public PageOption queryForPage(PageOption pageOption,String username) {
        pageOption.addSearch("username",username);
        List<Criterion> criterions = new ArrayList<Criterion>();
        if(StringUtils.isNotEmpty(username))
            criterions.add(Restrictions.like("username",username));
        List<AdminUser> adminUsers = adminUserDao.queryForPage(pageOption,criterions);

        if(!adminUsers.isEmpty()){
            pageOption.setData(adminUsers);
        }
        return pageOption;
    }

}
