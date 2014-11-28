package com.easysoft.member.backend.manager.impl;

import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.manager.IAuthActionManager;
import com.easysoft.member.backend.manager.IFunAndOperManager;
import com.easysoft.member.backend.manager.IRoleAuthManager;
import com.easysoft.member.backend.model.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限点管理
 * @author andy
 */
@Service("authActionManager")
public class AuthActionManager extends GenericService<AuthAction> implements IAuthActionManager {
    @Autowired
    private IFunAndOperManager funAndOperManager;
    @Autowired
    private IRoleAuthManager roleAuthManager;
	@Transactional(propagation = Propagation.REQUIRED)
	public int add(AuthAction act) {
		this.baseDaoSupport.insert("auth_action", act);
		return this.baseDaoSupport.getLastId("auth_action");
	}

    
    public int add(AuthAction auth, int roleId) {
        int actId = add(auth);
        //增加角色权限关系表
       // this.executeSql("insert into t_role_auth(roleid,authid)values(?,?)",roleId,actId);
        return actId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
	public void delete(int actid) {
		//删除角色权限表中对应的数据
		this.baseDaoSupport.execute("delete from role_auth where authid=?", actid);
		//删除权限基本数据
		this.baseDaoSupport.execute("delete from auth_action where actid=?", actid);
	}

    
    public int batAddRoleAuth(Integer roleId, List<FunAndOper> funAndOpers) {
        RoleAuth roleAuth;
        Role role = new Role();
        role.setId(roleId);

        List<RoleAuth> roleAuths = null;//roleAuthManager.findByProperty(RoleAuth.class,"role.id",roleId);
        for(RoleAuth roleAuthTemp : roleAuths){
            //funAndOperManager.deleteEntityById(FunAndOper.class,roleAuthTemp.getFunOrDataId());
        }
        //删除角色权限表中对应的数据
        this.baseDaoSupport.execute("delete from role_auth where role_id=?", roleId);
        for(FunAndOper funAndOper : funAndOpers){
           /* funAndOperManager.save(funAndOper);
            roleAuth = new RoleAuth();
            roleAuth.setRole(role);
            roleAuth.setAuthType(RoleAuth.AuthType.FUNCTION);
            roleAuth.setFunOrDataId(funAndOper.getId());
            roleAuthManager.save(roleAuth);*/
        }

        return 0;
    }

    public void edit(AuthAction act) {
       // this.updateEntitie(act);
	}

	
	public List<AuthAction> list() {
		
		return this.baseDaoSupport.queryForList("select * from auth_action", AuthAction.class);
	}


	public AuthAction get(int authid) {
		return this.baseDaoSupport.queryForObject("select * from auth_action where actid=?", AuthAction.class, authid);
	}

    public void deleteMenu(int actid, Integer[] menuidAr) {
        if (menuidAr == null) return;
        AuthAction authAction = get(actid);
        String menuStr = authAction.getObjvalue();
        if (StringUtils.isEmpty(menuStr)) {
            return;
        }

        String[] oldMenuAr = StringUtils.split(menuStr, ",");
        menuStr.split(",");
        oldMenuAr = delete(menuidAr, oldMenuAr);
        menuStr = StringUtil.arrayToString(oldMenuAr, ",");
        authAction.setObjvalue(menuStr);
        edit(authAction);
    }

    public static String[] delete(Integer[] ar1, String[] ar2)
/*     */   {
/* 138 */     List newList = new ArrayList();
/* 139 */     boolean flag = false;
/* 140 */     for (String num2 : ar2) {
/* 141 */       flag = false;
/* 142 */       for (Integer num1 : ar1) {
/* 143 */         if (num1.intValue() == Integer.valueOf(num2).intValue()) {
/* 144 */           flag = true;
/* 145 */           break;
/*     */         }
/*     */       }
/*     */
/* 149 */       if (!flag) {
/* 150 */         newList.add(num2);
/*     */       }
/*     */     }
/*     */
/* 154 */     return (String[])newList.toArray(new String[newList.size()]);
/*     */   }

    public void addMenu(int actid, Integer[] menuidAr)
/*     */   {
/*  60 */     if (menuidAr == null) return;
/*     */
/*  62 */     AuthAction authAction = get(actid);
/*  63 */     String menuStr = authAction.getObjvalue();
/*  64 */     if (StringUtil.isEmpty(menuStr)) {
/*  65 */       menuStr = StringUtil.arrayToString(menuidAr, ",");
/*  66 */       authAction.setObjvalue(menuStr);
/*     */     } else {
/*  68 */       String[] oldMenuAr = StringUtils.split(menuStr, ",");
/*  69 */       oldMenuAr = merge(menuidAr, oldMenuAr);
/*  70 */       menuStr = StringUtil.arrayToString(oldMenuAr, ",");
/*  71 */       authAction.setObjvalue(menuStr);
/*     */     }
/*     */
/*  74 */     edit(authAction);
/*     */   }

    private static String[] merge(Integer[] ar1, String[] ar2)
/*     */   {
/* 104 */     List newList = new ArrayList();
/* 105 */     for (String num : ar2) {
/* 106 */       newList.add(num);
/*     */     }
/*     */
/* 110 */     boolean flag = false;
/* 111 */     for (Integer num1 : ar1) {
/* 112 */       flag = false;
/*     */
/* 114 */       for (String num2 : ar2) {
/* 115 */         if (num1.intValue() == Integer.valueOf(num2).intValue()) {
/* 116 */           flag = true;
/* 117 */           break;
/*     */         }
/*     */       }
/*     */
/* 121 */       if (!flag) {
/* 122 */         newList.add(String.valueOf(num1));
/*     */       }
/*     */
/*     */     }
/*     */
/* 127 */     return (String[])newList.toArray(new String[newList.size()]);
/*     */   }



    
    public void saveAuth(Integer roleId,Integer operId,boolean isCheck,String[] menuIds) {
        for(String menuStr : menuIds){
            Integer menuId = Integer.parseInt(menuStr);
            RoleAuth roleAuthResult = roleAuthManager.queryRoleAuthByRoleIdAndFunId(roleId,menuId);
            //不存在此功能
            if(roleAuthResult==null){
                //保存功能权限
                RoleAuth roleAuth = new RoleAuth();
                roleAuth.setRoleId(roleId);
                roleAuth.setFunId(menuId);
                roleAuth.setOperids(operId+"");
                roleAuthManager.save(roleAuth);
            }else{
                String operation = "";
                if(isCheck){
                    operation = roleAuthResult.getOperids()+","+operId;
                }else{

                    String[] operations = roleAuthResult.getOperids().split(",");
                    for(String opt:operations){

                        if(!opt.equals(operId+"")){

                            operation += opt +",";


                        }
                    }
                    if(operation.endsWith(",")) operation = operation.substring(0,operation.length()-1);
                }
                roleAuthResult.setOperids(operation);
                roleAuthManager.update(roleAuthResult);
            }

        }

    }

    
    public void saveAuth(RoleAuth[] roleAuths) {
        if(roleAuths.length>0){
            roleAuthManager.deleteByRoleId(roleAuths[0].getRoleId());
        }
        for(RoleAuth roleAuth:roleAuths){

            roleAuthManager.save(roleAuth);
        }

    }
}
