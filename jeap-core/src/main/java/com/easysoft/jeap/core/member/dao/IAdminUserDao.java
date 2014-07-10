package com.easysoft.jeap.core.member.dao;

import com.easysoft.jeap.core.member.entity.AdminUser;
import com.easysoft.jeap.framework.db.PageOption;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangxa on 2014/7/9.
 */

public interface IAdminUserDao {
    public void save(AdminUser adminUser);
    public List<AdminUser> queryForPage(PageOption pageOption);
}
