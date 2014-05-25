package com.easysoft.core.manager;

import com.easysoft.core.common.service.IGenericService;
import com.easysoft.core.model.FormEntity;

import java.util.List;

/**
 * User: andy
 * Date: 14-4-1
 * Time: 上午11:04
 *
 * @since:
 */
public interface IFormManager extends IGenericService<FormEntity> {
    /**
     * 获取所有表单
     * @return
     */
    public List list( ) ;

    public void addForm(FormEntity entity);

    public FormEntity getFormById(Integer id);

    public FormEntity getFormById(Integer id,String type);

    public void delFormById(Integer id);

}