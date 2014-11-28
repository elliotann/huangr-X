package com.easysoft.component.form.manager;

import com.easysoft.component.form.model.FormEntity;

import java.util.List;

/**
 * User: andy
 *
 * @since: 1.0
 */
public interface IFormManager {
    /**
     * 获取所有表单
     * @return
     */
    public List list( ) ;

    public void addForm(FormEntity entity);

    public FormEntity getFormById(Integer id);

    public FormEntity getFormById(Integer id,String type);

    public void delFormById(Integer id);

    /**
     * 同步数据库
     * @param formId
     */
    public void synDb(Integer formId);

    /**
     * 根据编码查询表单
     * @param code
     * @return
     */
    public FormEntity queryFormByCode(String code);

}
