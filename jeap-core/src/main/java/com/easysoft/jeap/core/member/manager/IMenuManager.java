package com.easysoft.jeap.core.member.manager;

import com.easysoft.jeap.core.member.entity.Menu;

import java.util.List;

/**
 * Created by Administrator on 2014/7/19.
 */
public interface IMenuManager {
    public List<Menu> queryForAll();
    public Menu  queryById(Integer id);
    public List<Menu> queryMenusByPid(Integer pid);
    public void save(Menu menu);
    public void update(Menu menu);
}
