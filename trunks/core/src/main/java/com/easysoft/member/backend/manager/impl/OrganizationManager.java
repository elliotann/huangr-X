package com.easysoft.member.backend.manager.impl;


import com.easysoft.member.backend.dao.IOrganizatiOnDao;
import com.easysoft.member.backend.manager.ICompanyManager;
import com.easysoft.member.backend.manager.IDepartManager;
import com.easysoft.member.backend.manager.IOrganizationManager;

import com.easysoft.member.backend.model.Company;
import com.easysoft.member.backend.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("organizatiOnService")
@Transactional
public class OrganizationManager implements IOrganizationManager {
    @Autowired
    private IOrganizatiOnDao organizatiOnDao;
    @Autowired
    private ICompanyManager companyManager;
    @Autowired
    private IDepartManager departManager;

 	
 	public void save(Organization organizatiOn) {
        organizatiOnDao.save(organizatiOn);
 	}
    public void update(Organization organizatiOn){
        organizatiOnDao.update(organizatiOn);
    }
    public List<Organization> queryForList(){
        return organizatiOnDao.queryForList();
    }

    @Override
    public List<Organization> queryForTree(int orgid) {
        List<Company> orgList  = companyManager.queryForList();
        List<Organization> topOrgList  = new ArrayList<Organization>();
        for(Organization org :orgList){
            if(org.getPid().compareTo(orgid)==0){
                List<Organization> children = this.getChildren(orgList, org.getId());
                org.setChildren(children);
                topOrgList.add(org);
            }
        }
        return topOrgList;
    }

    /**
     * 在一个集合中查找子
     * @param compList 所有菜单集合
     * @param pid 父id
     * @return 找到的子集合
     */
    private List<Organization> getChildren(List<Company> compList ,int pid){
        List<Organization> children =new ArrayList<Organization>();
        for(Organization org :compList){
            if(org.getPid()==pid){
                org.setChildren(this.getChildren(compList, org.getId()));
                children.add(org);
            }
        }
        return children;
    }



    public Organization queryById(Integer id){
        return organizatiOnDao.queryById(id);
    }
    public void deleteById(Integer id){
        organizatiOnDao.deleteById(id);
    }

    @Override
    public List<Organization> queryOrganizationByTree(int pid) {
        //找出所有公司
        List<Company> orgList  = companyManager.queryForList();
        List<Organization> topOrgList  = new ArrayList<Organization>();
        for(Organization org : orgList){
            if(org.getPid()==pid){
                List<Organization> children = this.getChildren(orgList, org.getId());
                org.setChildren(children);
                topOrgList.add(org);
            }
        }
        return topOrgList;
    }
}