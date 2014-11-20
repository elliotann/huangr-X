package com.easysoft.member.backend.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.framework.db.PageOption;
import com.easysoft.framework.exception.ErrorCode;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.dao.IRoleDao;
import com.easysoft.member.backend.dao.IUserRoleDao;
import com.easysoft.member.backend.manager.IRoleManager;
import com.easysoft.member.backend.model.Role;

/**
 * 角色管理
 * @author andy
 * @since : 1.0
 */
@Service("roleManager")
@Transactional
public class RoleManager implements IRoleManager {
    public enum RoleManagerError{
        @ErrorCode(comment = "编辑角色时id不可为空!")
        ROLE_ID_NULL,
        @ErrorCode(comment = "密码不能为空!")
        PASSWORD_NULL,
        @ErrorCode(comment = "密码不正确!")
        PASSWORD_ERROR,
        @ErrorCode(comment = "用户名不正确!")
        USERNAME_ERROR

    }
    @Autowired
    private IdentityService identityService;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IUserRoleDao userRoleDao;
	/**
	 * 添加一个角色
	 * @param role 角色实体
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Role role) {
		
		//添加角色并
        roleDao.save(role);
        //添加到审批用户组中
        synRoleToActiviti(role,true);
	}

    /**
     * 同步所有角色数据到{@link Group}
    */
    private void synRoleToActiviti(Role role,boolean  synRoleToApprove) {
        if(synRoleToApprove){
            String groupId = role.getId()+"";
            Group group = identityService.newGroup(groupId);
            group.setName(role.getRolename());
           // group.setType(role.getType());
            identityService.saveGroup(group);
        }

    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Role role, int[] authids) {
        //校验参数
        if(role.getId()==0){
            throw new IllegalArgumentException("编辑角色时id不可为空");
        }
        if(StringUtil.isEmpty( role.getRolename())) throw new IllegalArgumentException("编辑角色时名称不可为空");
        roleDao.update(role);
       /* //清除角色的权限
        this.executeSql("delete from t_role_auth where roleid=?", role.getRoleid());

        //为这个角色 赋予权限点，写入角色权限对照表
        for(int authid:authids){
            this.executeSql("insert into t_role_auth(roleid,authid)values(?,?)", role.getRoleid(),authid);
        }
        //更新角色基本信息
        this.updateEntitie(role);*/
    }
	/**
	 * 删除某角色
	 * @param roleid
	 */
	public void deleteById(int roleid) {

		/*


		//删除角色权限
        this.executeSql("delete from t_role_auth where roleid =?", roleid);

		//删除角色
        this.executeSql("delete from t_role where roleid =?", roleid);*/
		//删除用户角色
		userRoleDao.delUserRoleByRoleId(roleid);
        roleDao.deleteById(roleid);
        //删除审批角色
        //identityService.deleteGroup(roleid+"");
	}


	/**
	 * 读取所有角色列表
	 * @return
	 */
	public List<Role> list() {

		return roleDao.queryForList();

	}




    
    public Role getRoleByName(String roleName,int roleid) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("rolename",roleName);
        params.put("roleid",roleid);
        return roleDao.getRoleByName(params);
    }
    /**
     * 读取某个角色信息，同时读取此角色权限
     * @param roleid
     * @return 权限id存于role.actids数组中
     */
    public Role queryById(int roleid){
       /* String sql ="select * from t_auth_action where actid in(select authid from t_role_auth where roleid =?)";
        List  actlist = this.daoSupport.queryForList(sql,AuthAction.class, roleid);*/
        Role role =  roleDao.queryById(roleid);

        /*if(actlist!=null){
            int[] actids = new int[ actlist.size()];
            for(int i=0;i<actlist.size();i++){
                actids[i] =( (AuthAction)actlist.get(i)).getActid();
            }
            role.setActids(actids);
        }*/
        return  role;
    }

    
    public PageOption queryByPage(PageOption pageOption, String rolename) {
        pageOption.addSearch("rolename",rolename);
        List<Criterion> criterions = new ArrayList<Criterion>();
        List<Role> roles = roleDao.queryForPage(pageOption,criterions);
        if(StringUtils.isNotEmpty(rolename)){
        	criterions.add(Restrictions.like("rolename",rolename));
        }
        if(!roles.isEmpty()){
            pageOption.setData(roles);
        }
        return pageOption;
    }
}
