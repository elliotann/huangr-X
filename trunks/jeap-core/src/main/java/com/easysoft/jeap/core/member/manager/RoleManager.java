package com.easysoft.jeap.core.member.manager;

import com.easysoft.jeap.core.member.dao.IMenuDao;
import com.easysoft.jeap.core.member.dao.IRoleDao;
import com.easysoft.jeap.core.member.entity.AdminUser;
import com.easysoft.jeap.core.member.entity.Menu;
import com.easysoft.jeap.core.member.entity.Role;
import com.easysoft.jeap.framework.db.PageOption;
import com.easysoft.jeap.framework.exception.ErrorCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2014/7/19.
 */
@Service("roleManager")
public class RoleManager implements IRoleManager {
    private static final Log logger = LogFactory.getLog(RoleManager.class);
    public enum RoleManagerError {
        /**
         * When property name is null.
         */
        @ErrorCode(comment = "角色名不能为空!")
        ROLE_NAME_NULL,
    }
    @Autowired
    private IRoleDao roleDao;

    @Override
    public PageOption queryByPage(PageOption pageOption) {
        if(pageOption==null) return null;
        List<Role> roles = roleDao.queryForPage(pageOption);
        pageOption.setData(roles);
        return pageOption;
    }

    @Override
    public List<Role> queryForAll() {
        return roleDao.queryForAll();
    }

    public Role  queryById(Integer id){
        return roleDao.queryById(id);
    }

    @Override
    public void save(Role role) {
        if(!role.validate()){
            throw new PermissionException(RoleManagerError.ROLE_NAME_NULL);
        }
        roleDao.save(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }




    @Override
    public void delMenu(Integer id) throws PermissionException{

        roleDao.deleteById(id);
    }
}
