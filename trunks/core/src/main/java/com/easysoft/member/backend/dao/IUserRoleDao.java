package com.easysoft.member.backend.dao;

import com.easysoft.member.backend.model.UserRole;

import java.util.List;

/**
 * @author : andy.huang
 * @since : 1.0
 */
public interface IUserRoleDao {
    /**
     * 根据用户id查询其所拥有的角色
     * @param userid
     * @return
     */
    public List<UserRole> queryRolesByUserId(Integer userid);
}
