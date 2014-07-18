package com.easysoft.jeap.core.member.manager;

import com.easysoft.jeap.core.member.entity.AdminUser;
import com.easysoft.jeap.framework.db.PageOption;

import java.util.List;

/**
 * Created by huangxa on 2014/7/9.
 */
public interface IAdminUserManager {
    public void save(AdminUser adminUser);
    public void update(AdminUser adminUser);
    public PageOption queryByPage(PageOption pageOption);
    public AdminUser queryAdminUserById(Integer id);
    public AdminUser queryByUserName(Integer id,String username);
    public AdminUser queryByEmail(Integer id,String email);

    public boolean isExistUsernameOrEmail(Integer id,String username,String email);

    public void deleteById(Integer id);
}
