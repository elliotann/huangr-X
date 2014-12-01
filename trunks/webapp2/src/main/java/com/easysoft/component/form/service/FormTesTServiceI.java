package com.easysoft.component.form.service;

import com.easysoft.component.form.entity.FormTesTEntity;
import java.util.List;
public interface FormTesTServiceI{

 	public void save(FormTesTEntity formTesT);
    public void update(FormTesTEntity formTesT);
    public List<FormTesTEntity> queryForList();
    public FormTesTEntity queryById(Integer id);
    public void deleteById(Integer id);
}
