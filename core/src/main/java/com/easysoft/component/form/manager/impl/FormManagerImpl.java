package com.easysoft.component.form.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.component.form.dao.IAddFormPageMetaDao;
import com.easysoft.component.form.dao.IFormDao;
import com.easysoft.component.form.dao.IFormFieldDao;
import com.easysoft.component.form.dao.IPageMetaDao;
import com.easysoft.component.form.manager.IFormManager;
import com.easysoft.component.form.model.AddFormPageMeta;
import com.easysoft.component.form.model.FormEntity;
import com.easysoft.component.form.model.FormField;
import com.easysoft.component.form.model.ListPageMeta;
import com.easysoft.core.dispatcher.core.freemarker.FreeMarkerParser;
import com.easysoft.core.solution.factory.DBSolutionFactory;
import com.easysoft.framework.db.dbsolution.IDBSolution;

/**
 * User: andy
 * Date: 14-4-1
 * Time: 上午11:05
 *
 * @since:
 */
@Service("formManager")
@Transactional
public class FormManagerImpl  implements IFormManager {
	private static final Log logger = LogFactory.getLog(FormManagerImpl.class);
    @Autowired
    private IFormDao formDao;
    @Autowired
    private IFormFieldDao formFieldDao;
    @Autowired
    private IPageMetaDao pageMetaDao;
    @Autowired
    private IAddFormPageMetaDao addFormPageMetaDao;
  
    public List list() {
        return formDao.queryForList();
    }


    public void addForm(FormEntity entity) {
        if(entity.getId()!=0){
            formDao.update(entity);
        }
        else{
            formDao.save(entity);
        }
        if(entity.getId()!=0)
            formFieldDao.delByFormId(entity.getId());
        
        for(FormField field : entity.getFields()){
        	ListPageMeta listPageMeta = new ListPageMeta();
        	
            field.setForm(entity);
            //增加页面元素
            listPageMeta.setField(field);
            listPageMeta.setWidth("150");
            field.setListPageMeta(listPageMeta);
            if(field.getId()!=0){
                formFieldDao.update(field);
            }else{
                formFieldDao.save(field);
            }
            
            pageMetaDao.save(listPageMeta);
        }
        
        
    }

   
    public FormEntity getFormById(Integer id) {
        FormEntity result = formDao.queryById(id);
        if(result==null) return null;
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("formId",id);
        List<FormField> fields = formFieldDao.queryForList(params);
     
        return result;
    }

  
    public FormEntity getFormById(Integer id,String type) {
        FormEntity result = formDao.queryById(id);
        if(result==null) return null;

        Map<String,Object> params = new HashMap<String, Object>();
        params.put("formId",id);
        List<FormField> fields = formFieldDao.queryForList(params);
        List<FormField> fieldsResult = new ArrayList<FormField>();
        if("form".equals(type)){
            for(FormField field : fields){
                if(field.isInform()){
                    fieldsResult.add(field);
                }
            }
            result.setFields(fieldsResult);
        }else{
            result.setFields(fields);
        }

        return result;
    }

   
    public void delFormById(Integer id) {
        FormEntity result = getFormById(id);
        for(FormField field : result.getFields()){
        	
            formFieldDao.deleteById(field.getId());
        }
        formDao.deleteById(id);
    }


    public void synDb(Integer formId) {
        //获取表单
        FormEntity formEntity = this.getFormById(formId);
        FreeMarkerParser freeMarkerParser = FreeMarkerParser.getInstance();
        freeMarkerParser.setClz(this.getClass());
        freeMarkerParser.setPageName("TableTemplate");
        Map<String,Object> datas = new HashMap<String,Object>();
        datas.put("formEntity",formEntity);
        freeMarkerParser.putData(datas);
        String xml = freeMarkerParser.processXmlContent();
        logger.info(xml);
        IDBSolution dbsolution = DBSolutionFactory.getDBSolution();
        dbsolution.dbImport(xml);
        formEntity.setIsSynDB("1");
        formDao.update(formEntity);
    }


    public FormEntity queryFormByCode(String code) {
        FormEntity result = formDao.queryFormByCode(code);
        if(result==null) return null;
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("formId",result.getId());
        List<FormField> fields = formFieldDao.queryForList(params);
        List<ListPageMeta> listPageMetas = pageMetaDao.queryForList(params);
        List<AddFormPageMeta> addFormPageMetas = addFormPageMetaDao.queryForList(params);
        
        result.setFields(fields);
 
        return result;
    }
}
