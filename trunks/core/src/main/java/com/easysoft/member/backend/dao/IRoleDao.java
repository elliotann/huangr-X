package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.Role;

import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
public interface IRoleDao extends IGenericDao<Role,Integer> {
    public Role getRoleByName(Map<String,Object> condition);
}
