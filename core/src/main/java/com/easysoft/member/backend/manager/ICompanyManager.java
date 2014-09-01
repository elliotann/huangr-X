package com.easysoft.member.backend.manager;

import com.easysoft.member.backend.model.Company;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
public interface ICompanyManager {
    public List<Company> queryForList();

    public Company queryById(int id);
}
