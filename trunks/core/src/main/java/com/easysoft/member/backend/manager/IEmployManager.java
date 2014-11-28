package com.easysoft.member.backend.manager;

import com.easysoft.framework.db.PageOption;
import com.easysoft.member.backend.model.Employ;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
public interface IEmployManager {
    /**
     * 分页查询
     * @param pageOption
     * @return
     */
    public PageOption queryForPage(PageOption pageOption);
    public List<Employ> queryForList();
    public void saveEmploy(Employ employ);
    public void updateEmploy(Employ employ);
    public Employ queryByNoAndId(String empNo,int id);
    public Employ queryById(int id);

    public void batchDel(Integer[] ids);
}
