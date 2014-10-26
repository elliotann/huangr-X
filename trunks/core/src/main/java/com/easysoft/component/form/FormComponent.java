package com.easysoft.component.form;

import com.easysoft.core.manager.IMenuManager;
import com.easysoft.framework.component.IComponent;
import com.easysoft.member.backend.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : andy.huang
 * @since :
 */
@Component
public class FormComponent implements IComponent {
    @Autowired
    private IMenuManager menuManager;

    public void install() {
        //安装菜单
        Menu pmenu = new Menu();
        pmenu.setTitle("动态表单");
        pmenu.setUrl("");
        pmenu.setPid(7);
        pmenu.setMenutype(2);
        pmenu.setSorder(50);
        pmenu.setSelected(0);
        pmenu.setDeleteflag(0);
        Integer pid = menuManager.add(pmenu);

        Menu menu = new Menu();
        menu.setTitle("表单配置");
        menu.setUrl("/core/admin/designer.do?list");
        menu.setPid(pid);
        menu.setMenutype(2);
        menu.setSorder(50);
        menu.setSelected(0);
        menu.setDeleteflag(0);
        menuManager.add(menu);
    }


    public void unInstall() {
        menuManager.deleteMenuByNameAndUrl("表单配置","/core/admin/designer.do?list");
        menuManager.deleteMenuByNameAndUrl("动态表单",null);
    }
}
