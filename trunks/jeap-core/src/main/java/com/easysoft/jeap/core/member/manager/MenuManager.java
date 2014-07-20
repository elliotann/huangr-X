package com.easysoft.jeap.core.member.manager;

import com.easysoft.jeap.core.member.dao.IMenuDao;
import com.easysoft.jeap.core.member.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2014/7/19.
 */
@Service("menuManager")
public class MenuManager implements IMenuManager {
    @Autowired
    private IMenuDao menuDao;
    @Override
    public List<Menu> queryForAll() {
        return menuDao.queryForAll();
    }

    public Menu  queryById(Integer id){
        return menuDao.queryById(id);
    }

    @Override
    public void save(Menu menu) {
        menuDao.save(menu);
    }

    @Override
    public void update(Menu menu) {
        menuDao.update(menu);
    }

    @Override
    public List<Menu> queryMenusByPid(Integer pid) {
        return menuDao.queryMenusByPid(pid);
    }
}
