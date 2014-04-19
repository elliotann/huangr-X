package com.easysoft.member.backend.manager.impl;

import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.manager.IRoleManager;
import com.easysoft.member.backend.model.AuthAction;
import com.easysoft.member.backend.model.Role;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色管理
 * @author andy
 * @since : 1.0
 */
@Service("roleManager")
public class RoleManager extends GenericService<Role> implements IRoleManager {
    @Autowired
    private IdentityService identityService;
	/**
	 * 添加一个角色
	 * @param role 角色实体
	 * @param authids 此角色的权限集合
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Role role, int[] authids) {
		
		//添加角色并
        this.save(role);
        //添加到审批用户组中
        synRoleToActiviti(role,true);
		//不赋予权限则直接返回
		if(authids==null) return ;

		//为这个角色 赋予权限点，写入角色权限对照表
		for(int authid:authids){
            this.executeSql("insert into role_auth(roleid,authid)values(?,?)", role.getRoleid(),authid);
		}
		
	}

    /**
     * 同步所有角色数据到{@link Group}
    */
    private void synRoleToActiviti(Role role,boolean  synRoleToApprove) {
        if(synRoleToApprove){
            String groupId = role.getRoleid()+"";
            Group group = identityService.newGroup(groupId);
            group.setName(role.getRolename());
           // group.setType(role.getType());
            identityService.saveGroup(group);
        }

    }

	/**
	 * 删除某角色
	 * @param roleid
	 */	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int roleid) {
		
		//删除用户角色
        this.executeSql("delete from t_user_role where roleid=?", roleid);

		//删除角色权限
        this.executeSql("delete from t_role_auth where roleid =?", roleid);
		
		//删除角色
        this.executeSql("delete from t_role where roleid =?", roleid);
        //删除审批角色
        identityService.deleteGroup(roleid+"");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void edit(Role role, int[] authids) {
		//校验参数 
		if(role.getRoleid()==0) throw new IllegalArgumentException("编辑角色时id不可为空");
		if(StringUtil.isEmpty( role.getRolename())) throw new IllegalArgumentException("编辑角色时名称不可为空");
		
		/*//清除角色的权限
        this.executeSql("delete from t_role_auth where roleid=?", role.getRoleid());

		//为这个角色 赋予权限点，写入角色权限对照表
		for(int authid:authids){
            this.executeSql("insert into t_role_auth(roleid,authid)values(?,?)", role.getRoleid(),authid);
		}		*/
		//更新角色基本信息
        this.updateEntitie(role);
	}

	/**
	 * 读取所有角色列表 
	 * @return
	 */
	public List<Role> list() {
		
		return this.baseDaoSupport.queryForList("select * from role", Role.class);
		
	}

	
	/**
	 * 读取某个角色信息，同时读取此角色权限
	 * @param roleid
	 * @return 权限id存于role.actids数组中
	 */
	public Role get(int roleid){
		String sql ="select * from t_auth_action where actid in(select authid from t_role_auth where roleid =?)";
		List  actlist = this.daoSupport.queryForList(sql,AuthAction.class, roleid);
		Role role =  this.get(Role.class,roleid);
		
		if(actlist!=null){
			int[] actids = new int[ actlist.size()];
			for(int i=0;i<actlist.size();i++){
				actids[i] =( (AuthAction)actlist.get(i)).getActid();
			}
			role.setActids(actids);
		}
		return  role;
	}

    @Override
    public Role getRoleByName(String roleName,int roleid) {
        String hql = "from Role r where r.rolename = '"+roleName+"'";
        if(roleid!=0){
            hql += " and roleid!="+roleid;
        }
        List<Role> roles = this.findByQueryString(hql);
        if(roles.isEmpty()) return null;
        return roles.get(0);
    }
}
