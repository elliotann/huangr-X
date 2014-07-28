package com.easysoft.jeap.core.member.manager;

import com.easysoft.jeap.core.member.entity.Menu;
import com.easysoft.jeap.core.member.entity.Role;
import com.easysoft.jeap.framework.db.PageOption;

import java.util.List;

/**
 * Created by Administrator on 2014/7/19.
 */
public interface IRoleManager {
    /**
     * 分页查询
     * @param pageOption
     * @return
     */
    public PageOption queryByPage(PageOption pageOption);
    public List<Role> queryForAll();
    public Role  queryById(Integer id);

    public void save(Role role);
    public void update(Role role);


    public void delRole(Integer id) throws PermissionException;

    public void batchDelRole(Integer[] ids);
}
