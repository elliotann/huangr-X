package com.easysoft.jeap.core.member.dao;

import com.easysoft.jeap.core.member.entity.AdminUser;
import com.easysoft.jeap.core.member.entity.Menu;
import com.easysoft.jeap.core.member.entity.Role;
import com.easysoft.jeap.framework.db.PageOption;

import java.util.List;

/**
 * CreatedMember by Administrator on 2014/7/19.
 */
public interface IRoleDao {
    public List<Role> queryForAll();
    public Role queryById(Integer id);
    public void save(Role role);
    public void update(Role role);
    public List<Role> queryRolesByPid(Integer pid);
    public void deleteById(Integer id);
    public List<Role> queryForPage(PageOption pageOption);
}
