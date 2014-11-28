package com.easysoft.member.backend.manager;

import com.easysoft.core.common.service.IGenericService;
import com.easysoft.member.backend.model.FunAndOper;

import java.util.List;

/**
 * User: andy
 * Date: 14-5-12
 * Time: 下午4:17
 *
 * @since:
 */
public interface IFunAndOperManager extends IGenericService<FunAndOper>{
    /**
     * 根据角色ID查询角色所有的权限
     * @param roleId
     * @return
     */
    public List<FunAndOper> queryFunAndOpersByRoleId(Integer roleId);
}
