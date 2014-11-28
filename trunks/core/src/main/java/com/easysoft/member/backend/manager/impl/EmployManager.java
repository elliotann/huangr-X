package com.easysoft.member.backend.manager.impl;

import com.easysoft.framework.db.PageOption;
import com.easysoft.member.backend.dao.IEmployDao;
import com.easysoft.member.backend.manager.IEmployManager;
import com.easysoft.member.backend.model.AdminUser;
import com.easysoft.member.backend.model.Employ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : andy.huang
 * @since : 1.0
 */
@Service
public class EmployManager implements IEmployManager {
    @Autowired
    private IEmployDao employDao;
    
    public List<Employ> queryForList() {
        return employDao.queryForList();
    }

    
    public void saveEmploy(Employ employ) {
        employDao.save(employ);
    }

    
    public Employ queryByNoAndId(String empNo, int id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("empNo",empNo);
        params.put("id",id);
        return employDao.queryEmployByCondition(params);
    }

    
    public PageOption queryForPage(PageOption pageOption) {
        List<Employ> employs = employDao.queryForPage(pageOption);
        if(!employs.isEmpty()){
            pageOption.setData(employs);
        }
        return pageOption;
    }

    
    public Employ queryById(int id) {
        return employDao.queryById(id);
    }

    
    public void updateEmploy(Employ employ) {
        employDao.update(employ);
    }

    
    public void batchDel(Integer[] ids) {
        for(Integer id:ids){
            employDao.deleteById(id);
        }
    }
}
