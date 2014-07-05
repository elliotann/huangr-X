package com.easysoft.jeap.service;

import com.easysoft.jeap.bean.UserTable;
import com.easysoft.jeap.dao.UserTableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangxa on 2014/6/30.
 */
@Service
public class UserTableService implements IUserTableService {
    @Autowired
    private UserTableDao userTableDao;
    @Override
    public List<UserTable> getUser() {
        return userTableDao.getUser();
    }
}
