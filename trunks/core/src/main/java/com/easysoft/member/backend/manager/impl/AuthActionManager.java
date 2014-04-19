package com.easysoft.member.backend.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.core.common.dao.spring.BaseSupport;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.manager.IAuthActionManager;
import com.easysoft.member.backend.model.AuthAction;

/**
 * 权限点管理
 * @author andy
 */
@Service("authActionManager")
public class AuthActionManager extends BaseSupport<AuthAction> implements IAuthActionManager {

	@Transactional(propagation = Propagation.REQUIRED)
	public int add(AuthAction act) {
		this.baseDaoSupport.insert("auth_action", act);
		return this.baseDaoSupport.getLastId("auth_action");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int actid) {
		//删除角色权限表中对应的数据
		this.baseDaoSupport.execute("delete from role_auth where authid=?", actid);
		//删除权限基本数据
		this.baseDaoSupport.execute("delete from auth_action where actid=?", actid);
	}

	
	public void edit(AuthAction act) {
		this.baseDaoSupport.update("auth_action", act, "actid="+act.getActid());
	}

	
	public List<AuthAction> list() {
		
		return this.baseDaoSupport.queryForList("select * from auth_action", AuthAction.class);
	}


	public AuthAction get(int authid) {
		return this.baseDaoSupport.queryForObject("select * from auth_action where actid=?", AuthAction.class, authid);
	}
    public void deleteMenu(int actid, Integer[] menuidAr)
/*     */   {
/*  81 */     if (menuidAr == null) return;
/*  82 */     AuthAction authAction = get(actid);
/*  83 */     String menuStr = authAction.getObjvalue();
/*  84 */     if (StringUtils.isEmpty(menuStr)) {
/*  85 */       return;
/*     */     }
/*     */
/*  88 */     String[] oldMenuAr = StringUtils.split(menuStr, ","); menuStr.split(",");
/*  89 */     oldMenuAr = delete(menuidAr, oldMenuAr);
/*  90 */     menuStr = StringUtil.arrayToString(oldMenuAr, ",");
/*  91 */     authAction.setObjvalue(menuStr);
/*  92 */     edit(authAction);
/*     */   }

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
}
