package com.easysoft.member.backend.manager;



import com.easysoft.member.backend.model.Organization;

import java.util.List;

public interface IOrganizationManager {

 	public void save(Organization organization);
    public void update(Organization organization);
    public List<Organization> queryForList();
    public List<Organization> queryForTree(int orgid);
    public Organization queryById(Integer id);
    public void deleteById(Integer id);

    public List<Organization> queryOrganizationByTree(int pid);
}
