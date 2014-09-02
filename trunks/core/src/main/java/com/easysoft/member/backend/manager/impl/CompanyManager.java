package com.easysoft.member.backend.manager.impl;

import com.easysoft.member.backend.dao.ICompanyDao;
import com.easysoft.member.backend.manager.ICompanyManager;
import com.easysoft.member.backend.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Company queryById(int id) {
        return companyDao.queryById(id);
    }

    @Override
    public void saveCompany(Company company) {
        companyDao.save(company);
    }

    @Override
    public void updateCompany(Company company) {
        companyDao.update(company);
    }

    @Override
    public Company queryByNoAndId(String compNo, int id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("compNo",compNo);
        params.put("id",id);
        return companyDao.queryByQry(params);
    }
}
