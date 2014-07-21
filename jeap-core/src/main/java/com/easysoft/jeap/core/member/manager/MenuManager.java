package com.easysoft.jeap.core.member.manager;

import com.easysoft.jeap.core.member.dao.IMenuDao;
import com.easysoft.jeap.core.member.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2014/7/19.
 */
@Service("menuManager")
public class MenuManager implements IMenuManager {
    @Autowired
    private IMenuDao menuDao;
    @Override
    public List<Menu> queryForAll() {
        return menuDao.queryForAll();
    }

    public Menu  queryById(Integer id){
        return menuDao.queryById(id);
    }

    @Override
    public void save(Menu menu) {
        menuDao.save(menu);
    }

    @Override
    public void update(Menu menu) {
        menuDao.update(menu);
    }

    @Override
    public List<Menu> queryMenusByPid(Integer pid) {
        List<Menu> menus = menuDao.queryMenusByPid(pid);
        for(Menu menu:menus){
            menu.setChildren(queryMenusByPid(menu.getId()));
        }
        return menus;
    }

    @Override
    public String getMenuJson() {
        StringBuffer json = new StringBuffer();
        json.append("var menu={");
        json.append("'app':[");
        json.append(getJson());
        json.append("]");
        json.append("}");
        return json.toString();
    }

    private String getJson(){
        StringBuffer json = new StringBuffer();
        List<Menu> rootMenus = queryMenusByPid(0);

        int i=0;
        for(Menu menu : rootMenus){
            json.append("{'id':");
            json.append(menu.getId());
            json.append(",'name':'");
            json.append(menu.getName()+"'");
            json.append(",'pid':");
            json.append(menu.getPid());
            json.append(",'url':'");
            json.append(menu.getUrl()+"'");
            if(menu.getChildren().size()>0){
                json.append(",'children':[");
                json.append(getChildrenJson(menu.getChildren()));
                json.append("]");
            }

            json.append("}");
            if(i!=rootMenus.size()-1){
                json.append(",");
            }
            i++;
        }
        return json.toString();
    }
    private String getChildrenJson(List<Menu> children){
        StringBuffer json = new StringBuffer();
        int i=0;
        for(Menu menu : children){
            json.append("{'id':");
            json.append(menu.getId());
            json.append(",'name':'");
            json.append(menu.getName()+"'");
            json.append(",'pid':");
            json.append(menu.getPid());
            json.append(",'url':'");
            json.append(menu.getUrl()+"'");
            if(menu.getChildren().size()>0){
                json.append(",'children':[");
                json.append(getChildrenJson(menu.getChildren()));
                json.append("]");
            }

            json.append("}");
            if(i!=children.size()-1){
                json.append(",");
            }
            i++;
        }

        return json.toString();
    }

    @Override
    public void delMenu(Integer id) throws Exception{
        List<Menu> menus = this.queryMenusByPid(id);
        if(!menus.isEmpty()){
            throw new Exception("有子菜单,不能删除");
        }
        menuDao.deleteById(id);
    }
}
