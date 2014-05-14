package com.easysoft.member.backend.manager.impl;

import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.context.webcontext.WebSessionContext;
import com.easysoft.member.backend.manager.IPermissionManager;
import com.easysoft.member.backend.model.*;
import com.easysoft.member.backend.vo.FunAndOperationVO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理
 * @author andy
 * @since : 1.0
 */
@Service("permissionManager")
public class PermissionManager extends GenericService implements IPermissionManager {
    public boolean checkHaveAuth(int actid) {
        WebSessionContext sessonContext = ThreadContextHolder.getSessionContext();

        AdminUser user = (AdminUser) sessonContext.getAttribute("admin_user_key");
        if (user.getFounder() == 1) return true;
        List<AuthAction> authList = user.getAuthList();
        for (AuthAction auth : authList) {
            if (auth.getActid() == actid) {
                return true;
            }
        }

        return false;
    }
	/**
	 * 为某个用户赋予某些角色<br>
	 * 会清除此用户的前角色，重新赋予
	 * @param userid  用户id
	 * @param roleids 角色id数组
	 */
	public void giveRolesToUser(int userid, int[] roleids) {
		this.baseDaoSupport.execute("delete from user_role where userid=?", userid);
		if(roleids==null) return ;
		for(int roleid:roleids){
			this.baseDaoSupport.execute("insert into user_role(roleid,userid)values(?,?)", roleid,userid);
		}
	}
	
	/**
	 * 读取某用户的权限点
	 * @param userid 用户id
	 * @param acttype 权限类型
	 * @return
	 */
	public List<AuthAction> getUesrAct(int userid, String acttype) {
		
		//查询权限表acttype符合条记录
		String sql ="select * from "+ this.getTableName("auth_action")+" where type=? ";
		//并且 权限id在用户的角色权限范围内
		sql+=" and actid in(select authid from  "+this.getTableName("role_auth")+" where roleid in ";
        //查询用户的角色列表
		sql+=" (select roleid from "+this.getTableName("user_role")+" where userid=?)";
		sql+=" )";
	 
		return this.daoSupport.queryForList(sql,AuthAction.class,acttype,userid);
	}

    @Override
    public List<FunAndOper> getUesrAct4New(int userid, String acttype) {
        //String sql = "select f.* from t_role_auth a where role_id in (SELECT roleid FROM t_user_role t where userid=?) and auth_type=? and f.fun_oper_id=a.funOrDataId";
        List<UserRole> userRoles = this.findHql("from UserRole ur where ur.adminUser.userid=?",userid);
        List<RoleAuth> results = new ArrayList<RoleAuth>();
        for(UserRole userRole : userRoles){
            List<RoleAuth> roleAuths = this.findHql("from RoleAuth ra where ra.role.id=? and ra.authType=?",userRole.getRole().getRoleid(),RoleAuth.AuthType.FUNCTION);
            results.addAll(roleAuths);
        }
        List<FunAndOper> funAndOpers = new ArrayList<FunAndOper>();
        for(RoleAuth roleAuth : results){
            funAndOpers.addAll(this.findHql("from FunAndOper fo where fo.id=?",roleAuth.getFunOrDataId()));

        }

        return funAndOpers;
    }

    /**
	 * 读取某用户的角色集合
	 * @param userid
	 * @return 此用户的角色集合
	 */	
	public List<Role> getUserRoles(int userid) {
		return this.baseDaoSupport.queryForList("select roleid from  user_role where userid=?", userid);
	}
	
	
	/**
	 * 删除某用户的所有角色
	 * @param userid 要删除角色的用户
	 */
	public void cleanUserRoles(int userid){
		this.baseDaoSupport.execute("delete from user_role where userid=?", userid);
	}

    @Override
    public List<AuthAction> getAuthActionsByRoleId(int roleId) {

        return this.baseDaoSupport.queryForList("select * from t_auth_action where actid in(select authid from t_role_auth where roleid=?)",new RowMapper(){

            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                AuthAction authAction = new AuthAction();
                authAction.setActid(rs.getInt("actid"));
                authAction.setName(rs.getString("name"));
                authAction.setType(rs.getString("type"));
                authAction.setObjvalue(rs.getString("objvalue"));
                return authAction;
            }
        },roleId);
    }

    @Override
    public List<FunAndOperationVO> getFunAndOperations() {
        List<FunAndOperationVO> funAndOperationVOs = this.baseDaoSupport.queryForList("select * from t_menu m where m.menutype=2",new RowMapper(){

            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                FunAndOperationVO funAndOperationVO = new FunAndOperationVO();
                funAndOperationVO.setMenuId(rs.getInt("id"));
                funAndOperationVO.setMenuName(rs.getString("title"));
                funAndOperationVO.setPid(rs.getInt("pid"));
                return funAndOperationVO;
            }
        });

        return funAndOperationVOs;
    }

}
