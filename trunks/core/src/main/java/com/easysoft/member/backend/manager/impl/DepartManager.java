package com.easysoft.member.backend.manager.impl;

import com.easysoft.framework.exception.ErrorCode;
import com.easysoft.member.backend.dao.IDepartDao;
import com.easysoft.member.backend.manager.IDepartManager;
import com.easysoft.member.backend.manager.PermissionManagerException;
import com.easysoft.member.backend.model.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Service("departManager")
public class DepartManager implements IDepartManager {
    public enum DepartMgrError{
        @ErrorCode(comment = "违法的参数!")
        ILLEGAL_PARAMS,
        @ErrorCode(comment = "参数depart为null!")
        DEPART_IS_NULL
    }
    @Autowired
    private IDepartDao departDao;
    @Override
    public void saveDepart(Depart depart) {
        if(depart == null){
            throw new PermissionManagerException(DepartMgrError.DEPART_IS_NULL);
        }
        if(!depart.validate()){
            throw new PermissionManagerException(DepartMgrError.ILLEGAL_PARAMS);
        }
        departDao.save(depart);
    }

    @Override
    public List<Depart> queryByOrgId(Integer orgId) {
        return departDao.queryByOrgId(orgId);
    }

    @Override
    public String queryDeparts4Select(Integer orgId) {
        List<Depart> departs = this.queryByOrgId(orgId);
        StringBuilder sb = new StringBuilder();
        for(Depart depart : departs){
            sb.append("<option value='");
            sb.append(depart.getId());
            sb.append("'>");
            sb.append(depart.getName());
            sb.append("</option>");
        }
        return sb.toString();
    }

    @Override
    public Depart queryById(int id) {
        return departDao.queryById(id);
    }

    @Override
    public Depart queryByNoAndId(String deptNo, int id) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("deptNo",deptNo);
        params.put("id",id);
        return departDao.queryByQry(params);
    }

    @Override
    public List<Depart> queryByCompIdAndPid(int compId, int pid) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("compId",compId);
        params.put("pid",pid);
        return departDao.queryByCompIdAndPid(params);
    }

    @Override
    public void updateDept(Depart dept) {
        departDao.update(dept);
    }
}
