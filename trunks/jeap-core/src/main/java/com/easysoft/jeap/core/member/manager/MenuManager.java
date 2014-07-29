package com.easysoft.jeap.core.member.manager;

import com.easysoft.jeap.core.member.dao.IMenuDao;
import com.easysoft.jeap.core.member.entity.Menu;
import com.easysoft.jeap.framework.exception.ErrorCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/7/19.
 */
@Service("menuManager")
public class MenuManager implements IMenuManager {
    private static final Log logger = LogFactory.getLog(MenuManager.class);
    public enum MenuManagerError {
        /**
         * When property name is null.
         */
        @ErrorCode(comment = "Has child menu!")
        HAS_CHILD_MENU,
    }
    @Autowired
    private IMenuDao menuDao;
    @Override
    public List<Menu> queryForAll() {
        return getChildren(0);
    }

    private List<Menu> getChildren(Integer pid){
        List<Menu> menus = menuDao.queryMenusByPid(pid);
        List<Menu> results = new ArrayList<Menu>();
        for(Menu menu:menus){
            results.add(menu);
            results.addAll(getChildren(menu.getId()));

            menu.setChildren(queryMenusByPid(menu.getId()));
        }



        return results;
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
    public void delMenu(Integer id) throws PermissionException{
        List<Menu> menus = this.queryMenusByPid(id);
        if(!menus.isEmpty()){
            throw new PermissionException(MenuManagerError.HAS_CHILD_MENU);
        }
        menuDao.deleteById(id);
    }
}
