package com.easysoft.component.form.dao;
import com.easysoft.core.common.dao.IGenericDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.easysoft.component.form.entity.FormTesTEntity;
import java.util.List;

public interface IFormTesTDao extends IGenericDao<FormTesTEntity,Integer> {

}