package com.easysoft.component.form.service.impl;
import  com.easysoft.component.form.dao.IFormTesTDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.easysoft.component.form.service.FormTesTServiceI;
import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.component.form.entity.FormTesTEntity;
import java.util.UUID;
import java.io.Serializable;
import java.util.List;
@Service("formTesTService")
@Transactional
public class FormTesTServiceImpl implements FormTesTServiceI {
    @Autowired
    private IFormTesTDao formTesTDao;

 	
 	public void save(FormTesTEntity formTesT) {
        formTesTDao.save(formTesT);
 	}
    public void update(FormTesTEntity formTesT){
        formTesTDao.update(formTesT);
    }
    public List<FormTesTEntity> queryForList(){
        return formTesTDao.queryForList();
    }
    public FormTesTEntity queryById(Integer id){
        return formTesTDao.queryById(id);
    }
    public void deleteById(Integer id){
        formTesTDao.deleteById(id);
    }
}