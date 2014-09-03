package com.easysoft.member.backend.manager.impl;

import com.easysoft.member.backend.dao.IEmployDao;
import com.easysoft.member.backend.manager.IEmployManager;
import com.easysoft.member.backend.model.Employ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
