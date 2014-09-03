package com.easysoft.member.backend.manager.impl;

import com.easysoft.member.backend.dao.IEmployDao;
import com.easysoft.member.backend.manager.IEmployManager;
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
    @Override
    public List<Employ> queryForList() {
        return employDao.queryForList();
    }

    @Override
    public void saveEmploy(Employ employ) {
        employDao.save(employ);
    }

    @Override
    public Employ queryByNoAndId(String empNo, int id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("empNo",empNo);
        params.put("id",id);
        return employDao.queryEmployByCondition(params);
    }
}
