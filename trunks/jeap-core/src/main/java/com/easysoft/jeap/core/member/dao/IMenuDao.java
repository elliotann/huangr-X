package com.easysoft.jeap.core.member.dao;

import com.easysoft.jeap.core.member.entity.AdminUser;
import com.easysoft.jeap.core.member.entity.Menu;

import java.util.List;

/**
 * CreatedMember by Administrator on 2014/7/19.
 */
public interface IMenuDao {
    public List<Menu> queryForAll();
    public Menu queryById(Integer id);
    public void save(Menu menu);
    public void update(Menu menu);
    public List<Menu> queryMenusByPid(Integer pid);
    public void deleteById(Integer id);;
}
