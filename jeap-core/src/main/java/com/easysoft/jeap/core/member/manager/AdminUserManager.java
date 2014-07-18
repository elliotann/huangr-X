package com.easysoft.jeap.core.member.manager;

import com.easysoft.jeap.core.member.dao.IAdminUserDao;
import com.easysoft.jeap.core.member.entity.AdminUser;
import com.easysoft.jeap.framework.db.PageOption;
import com.easysoft.jeap.framework.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangxa on 2014/7/9.
 */
@Service("adminUserManager")
public class AdminUserManager implements IAdminUserManager {
    @Autowired
    private IAdminUserDao adminUserDao;
    @Override
    public void save(AdminUser adminUser) {
        adminUser.setPassword(MD5Util.md5(adminUser.getPassword()));
        adminUserDao.save(adminUser);
    }

    @Override
    public PageOption queryByPage(PageOption pageOption) {
        if(pageOption==null) return null;
        List<AdminUser> adminUsers = adminUserDao.queryForPage(pageOption);
        pageOption.setData(adminUsers);
        return pageOption;
    }

    @Override
    public AdminUser queryAdminUserById(Integer id) {
        return adminUserDao.queryById(id);
    }

    @Override
    public void update(AdminUser adminUser) {
        adminUserDao.update(adminUser);
    }

    @Override
    public AdminUser queryByUserName(Integer id, String username) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        params.put("username",username);
        return adminUserDao.queryByUserNameOrEmail(params);
    }

    @Override
    public AdminUser queryByEmail(Integer id, String email) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        params.put("email",email);
        return adminUserDao.queryByUserNameOrEmail(params);
    }

    @Override
    public boolean isExistUsernameOrEmail(Integer id, String username, String email) {
        AdminUser adminUser = null;
        if(StringUtils.isNotEmpty(username)){
            adminUser = this.queryByUserName(id,username);
        }else if(StringUtils.isNotEmpty(email)){
            adminUser = this.queryByEmail(id,email);
        }
        return adminUser!=null;
    }

    @Override
    public void deleteById(Integer id) {
        adminUserDao.deleteById(id);
    }
}
