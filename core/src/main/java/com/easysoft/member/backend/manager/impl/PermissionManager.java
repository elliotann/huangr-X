package com.easysoft.member.backend.manager.impl;

import com.easysoft.core.common.dao.spring.BaseSupport;
import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.context.webcontext.WebSessionContext;
import com.easysoft.member.backend.dao.IOperationBtnDao;
import com.easysoft.member.backend.dao.IUserRoleDao;
import com.easysoft.member.backend.manager.IOperationBtnManager;
import com.easysoft.member.backend.manager.IPermissionManager;
import com.easysoft.member.backend.manager.IRoleAuthManager;
import com.easysoft.member.backend.model.*;
import com.easysoft.member.backend.vo.FunAndOperationVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PermissionManager extends BaseSupport implements IPermissionManager {
    @Autowired
    private IOperationBtnDao operationBtnDao;
    @Autowired
    private IRoleAuthManager roleAuthManager;
    @Autowired
    private IUserRoleDao userRoleDao;
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

    
    public List<OperationBtn> queryBtnByUsernameAndMenuId(Integer userid, String acttype,Integer menuId) {
        AdminUser adminUser = UserServiceFactory.getUserService().getCurrentUser();
        List<OperationBtn> results = new ArrayList<OperationBtn>();
        if(adminUser.getFounder()==1){
            return operationBtnDao.queryOperationsByMenuId(menuId);
        }

        List<RoleAuth> roleAuths = getUesrAct(userid,acttype);

        for(RoleAuth roleAuth : roleAuths){
            if(roleAuth.getFunId().intValue()==menuId.intValue()){
                String operation = roleAuth.getOperids();
                if(StringUtils.isNotEmpty(operation)){
                    String [] btns = operation.split(",");
                    for(String btnId : btns){
                        results.add(operationBtnDao.queryById(Integer.parseInt(btnId)));
                    }
                }
            }
        }
        return results;
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
	public List<RoleAuth> getUesrAct(int userid, String acttype) {
        List<RoleAuth> results = new ArrayList<RoleAuth>();
        List<UserRole> userRoles = userRoleDao.queryRolesByUserId(userid);
        for(UserRole userRole : userRoles){
            results.addAll(roleAuthManager.queryRoleAuthListByRoleId(userRole.getRole().getId()));
        }
		return results;
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

    
    public List<AuthAction> getAuthActionsByRoleId(int roleId) {

        return this.baseDaoSupport.queryForList("select * from t_auth_action where actid in(select authid from t_role_auth where roleid=?)",new RowMapper(){

            
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

    
    public List<FunAndOperationVO> getFunAndOperations() {
        List<FunAndOperationVO> funAndOperationVOs = this.baseDaoSupport.queryForList("select * from t_menu m where m.menutype=2",new RowMapper(){

            
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

    
    public List<OperationBtn> getOperationBtnsByMenuId(Integer menuId) {
        List<OperationBtn> operationBtns = operationBtnDao.queryOperationsByMenuId(menuId);
        return operationBtns;
    }

    
    public boolean hasOperationByRoleAndMenu(Integer roleId, Integer menuId,String operId) {
        RoleAuth roleAuth = roleAuthManager.queryRoleAuthByRoleIdAndFunId(roleId,menuId);

        if(roleAuth!=null&&roleAuth.getOperids()!=null){
            String operation = roleAuth.getOperids();
            String[] operations = operation.split(",");
            for(String operStr:operations){
                if(operStr.equals(operId)){
                    return true;
                }
            }
        }
        return false;

    }


}
