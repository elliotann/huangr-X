package com.easysoft.build.manager;


import com.easysoft.build.dao.BtDeployPackRuleDao;
import com.easysoft.build.dao.RespInfoDao;
import com.easysoft.build.model.BtDeployPackRule;
import com.easysoft.build.model.RepInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service("btDeployPackRuleService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class BtDeployPackRuleService{
	protected final static Logger logger = Logger.getLogger(BtDeployPackRuleService.class);
    @Autowired
    private RespInfoDao respInfoDao;
    @Autowired
    private BtDeployPackRuleDao btDeployPackRuleDao;
    public List<BtDeployPackRule> getBtDeployPackRuleListByBranch(String branch){
        RepInfo ri = respInfoDao.getRespInfoByName(branch);
        return btDeployPackRuleDao.getBtDeployPackRuleListByBranch(ri.getId());
    }
    public BtDeployPackRule getBtDeployPackRuleByBranchAndDname(String branch,String deployPackName){
        RepInfo ri = respInfoDao.getRespInfoByName(branch);
        return btDeployPackRuleDao.getBtDeployPackRuleByName(deployPackName,ri.getId());
    }
	/*
	

	
	@Override
	protected BaseExtendDao getDao() {
		return btDeployPackRuleDao;
	}
	

	

	
	public void saveBtDeployPackRule(BtDeployPackRule bdpr,String branch){
		RepInfo ri = respInfoDao.getRespInfoByName(branch);		
		BtDeployPackRule t = btDeployPackRuleDao.getBtDeployPackRuleByName(bdpr.getDeployPackName(), ri.getId());
		if(t == null){
			bdpr.setRi(ri);
			bdpr.setPassedTestes("1");
			btDeployPackRuleDao.saveBtDeployPackRule(bdpr);
		}else{
			t.setDeployPackName(bdpr.getDeployPackName());
			t.setPassedPerson(bdpr.getPassedPerson());
			t.setPassedTestes("1");
			btDeployPackRuleDao.saveBtDeployPackRule(t);
		}
		//调项目管理系统接口。
		synchronizedDPBM(branch, bdpr.getDeployPackName());
	}
	
	*//**
	 * 触发补丁包中相应构建单号的下一流程 。
	 * 
	 * @param branch
	 * @param patchName
	 *//*
	private void synchronizedDPBM(String branch, String patchName) {
		BuildFile[] bfs;
		try {
			bfs = PatchFileService.listBuildPackInfoOfPatch(branch, patchName + ".zip");
		} catch (ParseException e1) {
			throw new RuntimeException(e1);
		}
		String xmlStr = "<viewentries><viewentry snnum=\"%s\" Totaskid =\"%s\" username=\"杨明\" /></viewentries>";
		String sendData;
		String taskid = null;
		String fileName = null;
		String ret = null;
		for (BuildFile bf: bfs) {//单个任务调用？
			if (bf.getFileName().startsWith("B")) {
				taskid = "106-ce58bc5ff5844ffea9e3780d0fc648c9";
			} else if (bf.getFileName().startsWith("V")) {
				taskid = "106-83465473c25b4e8aa07447b91d20a625";
			} else continue;
			fileName = bf.getFileName().substring(0, bf.getFileName().lastIndexOf(".zip"));
			sendData = String.format(xmlStr, new Object[]{fileName, taskid});
			try {
			    ret = WebServiceManager.sendToBPDM(sendData);
			    logger.info(ret);
			} catch (Exception e) {
				throw new RuntimeException("构建包转向下一任务失败！[单号：" + fileName + "，下一任务id：" + taskid +",error:"+e.getMessage(), e);
			}
		}
	}
	
	public void delBtDeployPackRule(String deployPackName,String branch){	
		RepInfo ri = respInfoDao.getRespInfoByName(branch);	
		BtDeployPackRule bdpr = btDeployPackRuleDao.getBtDeployPackRuleByName(deployPackName, ri.getId());
		btDeployPackRuleDao.delBtDeployPackRule(bdpr);
	}*/

}
