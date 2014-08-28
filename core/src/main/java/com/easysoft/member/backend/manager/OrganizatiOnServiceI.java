package com.easysoft.member.backend.manager;



import com.easysoft.member.backend.model.OrganizatiOnEntity;

import java.util.List;

public interface OrganizatiOnServiceI {

 	public void save(OrganizatiOnEntity organizatiOn);
    public void update(OrganizatiOnEntity organizatiOn);
    public List<OrganizatiOnEntity> queryForList();
    public List<OrganizatiOnEntity> queryForTree();
    public OrganizatiOnEntity queryById(Integer id);
    public void deleteById(Integer id);
;
}
