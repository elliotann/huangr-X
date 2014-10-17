package com.easysoft.build.manager;


import com.easysoft.build.dao.BtBuildPackDetailDao;
import com.easysoft.build.dao.BuildConfigInfoDao;
import com.easysoft.build.dao.DeployPackInfoDao;
import com.easysoft.build.dao.RespInfoDao;
import com.easysoft.build.model.BtBuildPackDetail;
import com.easysoft.build.model.BuildConfigInfo;
import com.easysoft.build.model.DeployPackInfo;
import com.easysoft.build.model.RepInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("btBuildPackDetailService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class BtBuildPackDetailService{
    @Autowired
    private RespInfoDao respInfoDao;
    @Autowired
    private DeployPackInfoDao deployPackInfoDao;
    @Autowired
    private BuildConfigInfoDao buildConfigInfoDao;
    @Autowired
    private BtBuildPackDetailDao btBuildPackDetailDao;
	/*

	

	

	

	
	@Override
	protected BaseExtendDao getDao() {
		return btBuildPackDetailDao;
	}
	
	public BtBuildPackDetail getBtBuildPackDetail(long id){
		return btBuildPackDetailDao.getBtBuildPackDetail(id);
	}
	
	public BtBuildPackDetail getBtBuildPackDetailByNo(String no){
		return btBuildPackDetailDao.getBtBuildPackDetailByNo(no);
	}
	
	public void saveList(List<BuildDetailData> dl,BuildConfig config){
		RepInfo ri = respInfoDao.getRespInfoByName(config.getVersion());
		for(BuildDetailData d:dl){
			saveOrUpdate(d,config,ri);
		}
	}
	
	public void saveOrUpdate(BuildDetailData ddpd,BuildConfig config,RepInfo ri){
		BtBuildPackDetail e = btBuildPackDetailDao.getBtBuildPackDetailByNoRid(ddpd.getBuildNo(),ri);		
		if(e==null){
			e = new BtBuildPackDetail();
			e.setBuildPackName(ddpd.getBuildNo());
			e.setBuildPackDetail(ddpd.getDetail());
			e.setRi(ri);			
			btBuildPackDetailDao.saveBasePackageFile(e);
		}else{			
			e.setBuildPackName(ddpd.getBuildNo());
			e.setBuildPackDetail(ddpd.getDetail());			
			btBuildPackDetailDao.saveBasePackageFile(e);
		}		
	}
	

	*/

    public Map<String,List<BtBuildPackDetail>> getBtBuildPackDetailListByBd(String branch,String fileName){
        String buildName = fileName.substring(0,fileName.indexOf(".zip"));
        RepInfo ri = respInfoDao.getRespInfoByName(branch);
        List<DeployPackInfo> dplist = deployPackInfoDao.getDeployPackInfoListByName(buildName, ri.getId());
        Map<String,List<BtBuildPackDetail>> bmap = new HashMap<String,List<BtBuildPackDetail>>();
        List<BtBuildPackDetail> lbbpd = new ArrayList<BtBuildPackDetail>();
        List<BuildConfigInfo> lb = new ArrayList<BuildConfigInfo>();
        for(DeployPackInfo dp:dplist){
            lb = buildConfigInfoDao.getBuildConfigInfoByDeployId(String.valueOf(dp.getId()),branch);
            List<String> vpnames = new ArrayList<String>();
            String temp = "";
            String[] ta;
            for(BuildConfigInfo bc:lb){
                temp = bc.getVpNames();
                if(StringUtils.isNotEmpty(temp)){
                    ta = temp.split(";");
                    for(String t:ta){
                        vpnames.add(t);
                    }
                }
            }

            lbbpd = btBuildPackDetailDao.getBtBuildPackDetailByRidArray(vpnames,ri);
            bmap.put(dp.getDeployPackName(), lbbpd);
        }
        return bmap;
    }
	

}
