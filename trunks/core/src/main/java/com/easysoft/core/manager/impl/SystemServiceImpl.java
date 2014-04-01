package com.easysoft.core.manager.impl;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.core.manager.SystemService;
import com.easysoft.member.backend.model.Menu;
import com.easysoft.core.model.TSType;
import com.easysoft.core.model.TSTypegroup;
import com.easysoft.member.backend.model.AdminUser;

@Service("systemService")
@Transactional
public class SystemServiceImpl extends GenericService implements SystemService {
	public AdminUser checkUserExits(AdminUser user) throws Exception {
		return null;
	}

	/**
	 * 添加日志
	 */
	public void addLog(String logcontent, Short loglevel, Short operatetype) {
		
	}

	/**
	 * 根据类型编码和类型名称获取Type,如果为空则创建一个
	 * 
	 * @param typecode
	 * @param typename
	 * @return
	 */
	public TSType getType(String typecode, String typename, TSTypegroup tsTypegroup) {
		TSType actType =null;
		
		return actType;

	}

	/**
	 * 根据类型分组编码和名称获取TypeGroup,如果为空则创建一个
	 * 
	 * @param typecode
	 * @param typename
	 * @return
	 */
	public TSTypegroup getTypeGroup(String typegroupcode, String typgroupename) {
		TSTypegroup tsTypegroup = null;
	
		return tsTypegroup;
	}

	
	public TSTypegroup getTypeGroupByCode(String typegroupCode) {
		TSTypegroup tsTypegroup = null;
		return tsTypegroup;
	}

	
	public void initAllTypeGroups() {
		
	}

	
	public void refleshTypesCach(TSType type) {
		
	}

	
	public void refleshTypeGroupCach() {
		
	}

	// ----------------------------------------------------------------
	// ----------------------------------------------------------------

	
	public Set<String> getOperationCodesByRoleIdAndFunctionId(String roleId, String functionId) {
		
		return null;
	}

	
	public Set<String> getOperationCodesByUserIdAndFunctionId(String userId, String functionId) {
		
		return null;
	}

	// ----------------------------------------------------------------
	// ----------------------------------------------------------------
	
	public void flushRoleFunciton(String id, Menu newFunction) {
		
	}


}
