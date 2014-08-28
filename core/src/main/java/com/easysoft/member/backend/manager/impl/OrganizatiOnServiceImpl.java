package com.easysoft.member.backend.manager.impl;


import com.easysoft.member.backend.dao.IOrganizatiOnDao;
import com.easysoft.member.backend.manager.OrganizatiOnServiceI;
import com.easysoft.member.backend.model.OrganizatiOnEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("organizatiOnService")
@Transactional
public class OrganizatiOnServiceImpl implements OrganizatiOnServiceI {
    @Autowired
    private IOrganizatiOnDao organizatiOnDao;

 	
 	public void save(OrganizatiOnEntity organizatiOn) {
        organizatiOnDao.save(organizatiOn);
 	}
    public void update(OrganizatiOnEntity organizatiOn){
        organizatiOnDao.update(organizatiOn);
    }
    public List<OrganizatiOnEntity> queryForList(){
        return organizatiOnDao.queryForList();
    }

    @Override
    public List<OrganizatiOnEntity> queryForTree() {
        List<OrganizatiOnEntity> roots = organizatiOnDao.queryByPid(0);
        for(OrganizatiOnEntity organizatiOnEntity : roots){
            organizatiOnEntity.setChildren(organizatiOnDao.queryByPid(organizatiOnEntity.getId()));
        }
        return roots;
    }

    public OrganizatiOnEntity queryById(Integer id){
        return organizatiOnDao.queryById(id);
    }
    public void deleteById(Integer id){
        organizatiOnDao.deleteById(id);
    }


}