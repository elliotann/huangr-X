package com.easysoft.member.backend.manager;

import com.easysoft.member.backend.model.Depart;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
public interface IDepartManager {
    public void saveDepart(Depart depart);

    public List<Depart> queryByOrgId(Integer orgId);

    public String queryDeparts4Select(Integer orgId);

    public Depart queryById(int id);

}
