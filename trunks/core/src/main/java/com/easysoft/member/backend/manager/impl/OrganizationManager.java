package com.easysoft.member.backend.manager.impl;


import com.easysoft.member.backend.dao.IOrganizatiOnDao;
import com.easysoft.member.backend.manager.IOrganizationManager;

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
        List<Organization> orgList  = this.queryForList();
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
     * @param menuList 所有菜单集合
     * @param parentid 父id
     * @return 找到的子集合
     */
    private List<Organization> getChildren(List<Organization> menuList ,Integer parentid){
        List<Organization> children =new ArrayList<Organization>();
        for(Organization menu :menuList){
            if(menu.getPid().compareTo(parentid)==0){
                menu.setChildren(this.getChildren(menuList, menu.getId()));
                children.add(menu);
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


}