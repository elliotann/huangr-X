package com.easysoft.member.backend.manager.impl;

import com.easysoft.member.backend.dao.ICompanyDao;
import com.easysoft.member.backend.manager.ICompanyManager;
import com.easysoft.member.backend.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
@Service
public class CompanyManager implements ICompanyManager {
    @Autowired
    private ICompanyDao companyDao;
    @Override
    public List<Company> queryForList() {
        return companyDao.queryForList();
    }
}
