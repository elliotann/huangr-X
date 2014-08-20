package ${bussiPackage}.${entityPackage}.dao;
import com.easysoft.core.common.dao.IGenericDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import ${bussiPackage}.${entityPackage}.entity.${entityName}Entity;
import java.util.List;

public interface I${entityName}Dao extends IGenericDao<${entityName},Integer> {

}