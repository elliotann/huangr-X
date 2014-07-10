package com.easysoft.jeap.core.member.manager;

import com.easysoft.jeap.core.member.dao.IAdminUserDao;
import com.easysoft.jeap.core.member.entity.AdminUser;
import com.easysoft.jeap.framework.db.PageOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangxa on 2014/7/9.
 */
@Service("adminUserManager")
public class AdminUserManager implements IAdminUserManager {
    @Autowired
    private IAdminUserDao adminUserDao;
    @Override
    public void save(AdminUser adminUser) {
        adminUserDao.save(adminUser);
    }

    @Override
    public PageOption queryByPage(PageOption pageOption) {
        if(pageOption==null) return null;
        List<AdminUser> adminUsers = adminUserDao.queryForPage(pageOption);
        pageOption.setData(adminUsers);
        return pageOption;
    }
}
