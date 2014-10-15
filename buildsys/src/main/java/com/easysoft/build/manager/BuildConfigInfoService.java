/*
 *
 *  * Copyright 1999-2011 jeap Group Holding Ltd.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.easysoft.build.manager;


import com.easysoft.build.dao.BuildConfigInfoDao;
import com.easysoft.build.model.*;
import com.easysoft.framework.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service("patch_buildConfigInfoService")
@Transactional
public class BuildConfigInfoService{
	  @Autowired
	  private BuildConfigInfoDao buildConfigInfoDao;
	  @Autowired
	  private RespInfoDao respInfoDao;
	  @Autowired
	  private BuildConfigInfoLogDao buildConfigInfoLogDao;	  
	  @Autowired
	  private DeployPackInfoDao deployPackInfoDao;
	  @Autowired
	  private DeployPackInfoLogDao deployPackInfoLogDao;
	  @Autowired
	  private PrivatePackLogDao privatePackLogDao;
	  
	  
	  
	  @Override
	  protected BaseExtendDao getDao() {
		  return buildConfigInfoDao;
	  }	  
	  
	  public List<BuildConfigInfo> getBuildConfigInfoByDeployId(String id,String curBranch){		
		 return buildConfigInfoDao.getBuildConfigInfoByDeployId(id,curBranch);
	  }
	  
	  
	  public List<BuildConfigInfo> getBuildConfigInfoList(BuildConfigInfoSearchBean searchBean, PageControlData pgd,String curBranch){		
		  return buildConfigInfoDao.getBuildConfigInfoList(searchBean, pgd,curBranch);			
	  }
	  
	  public List<BuildConfigInfo> getBuildConfigInfoExportList(BuildConfigInfoSearchBean searchBean,String curBranch){		
		  return buildConfigInfoDao.getBuildConfigInfoExportList(searchBean,curBranch);			
	  }
	  
	  public BuildConfigInfo getBuildConfigInfo(String id){
		  return buildConfigInfoDao.getBuildConfigInfo(Long.valueOf(id));
	  }
	  
	  public void saveBuildConfigInfo(BuildConfigInfo bc){		
		  buildConfigInfoDao.saveBuildConfigInfo(bc);
	  }
	  
	  public void saveBcConfigInBuilding(BuildConfig config){
		  BuildConfigInfo bc = new BuildConfigInfo();		  
		  RepInfo ri = respInfoDao.getRespInfoByName(config.getVersion());
		  bc.setRi(ri);
		  bc.setBuildFileName(config.getId());
		  bc.setStatus("0");
		  bc.setAdder(config.getDevelopers());
		  bc.setAddTime(new Date());
		  bc.setBuildDepends(config.listDepends());
		  bc.setBuildComments(config.getComment());
		  bc.setIncludsFiles(config.getFiles());
		  bc.setHasSql(config.getHas_sql());
		  bc.setSqlFiles(config.getSqlFiles());
		  bc.setVpNames(config.getVps());
		  bc.setIncludsModules(config.getModules());
		  buildConfigInfoDao.saveBuildConfigInfo(bc);
		  //记录日志表
		  BuildConfigInfoLog log = new BuildConfigInfoLog();
		  log.setRi(ri);
		  log.setBc(bc);
		  log.setOperater(config.getDevelopers());
		  log.setOperaterCode("0");
		  log.setOperaterName("提交构建");
		  log.setOperaterTime(new Date());
		  buildConfigInfoLogDao.saveBuildConfigInfoLog(log);		  
	  }	  
	  
	  public void saveBcConfigInStartesting(BuildConfig config){	
		  RepInfo ri = respInfoDao.getRespInfoByName(config.getVersion());
		  BuildConfigInfo bc = buildConfigInfoDao.getBuildConfigInfoByNameNoCancel(config.getId(),ri);
		  if(bc!=null){
			  bc.setStatus("1");
			  bc.setStartTester(config.getTesters());
			  bc.setStartTestTime(new Date());
			  buildConfigInfoDao.saveBuildConfigInfo(bc);
			  //记录日志
			  BuildConfigInfoLog log = new BuildConfigInfoLog();
			  log.setRi(ri);
			  log.setBc(bc);
			  log.setOperater(config.getTesters());
			  log.setOperaterCode("1");
			  log.setOperaterName("开始测试");
			  log.setOperaterTime(new Date());
			  buildConfigInfoLogDao.saveBuildConfigInfoLog(log);		
		  }
	  }
	  
	  public void saveBcConfigInCancelTesting(BuildConfig config,String tester){	
		  RepInfo ri = respInfoDao.getRespInfoByName(config.getVersion());
		  BuildConfigInfo bc = buildConfigInfoDao.getBuildConfigInfoByNameNoCancel(config.getId(),ri);
		  if(bc!=null){
			  bc.setStatus("2");
			  bc.setCancelTester(tester);
			  bc.setCancelTestTime(new Date());
			  buildConfigInfoDao.saveBuildConfigInfo(bc);
			  //记录日志
			  BuildConfigInfoLog log = new BuildConfigInfoLog();			  
			  log.setRi(ri);
			  log.setBc(bc);
			  log.setOperater(tester);
			  log.setOperaterCode("2");
			  log.setOperaterName("取消测试"); 
			  log.setOperaterTime(new Date());
			  buildConfigInfoLogDao.saveBuildConfigInfoLog(log);	
		  }
	  }	
	  
	  public void saveBcConfigInPasstesting(BuildConfig config){	
		  RepInfo ri = respInfoDao.getRespInfoByName(config.getVersion());
		  BuildConfigInfo bc = buildConfigInfoDao.getBuildConfigInfoByNameNoCancel(config.getId(),ri);
		  if(bc!=null){
			  bc.setStatus("3");
			  bc.setPassTester(config.getTesters());
			  bc.setPassTestTime(new Date());
			  buildConfigInfoDao.saveBuildConfigInfo(bc);
			  //记录日志
			  BuildConfigInfoLog log = new BuildConfigInfoLog();			  
			  log.setRi(ri);
			  log.setBc(bc);
			  log.setOperater(config.getTesters());
			  log.setOperaterCode("3");
			  log.setOperaterName("测试通过");
			  log.setOperaterTime(new Date());
			  buildConfigInfoLogDao.saveBuildConfigInfoLog(log);	
		  }
	  }
	  
	  public void updateDeploys(BuildConfig config,RepInfo ri){
		  List<BuildConfigInfo> list = buildConfigInfoDao.getDependsBuildConfigInfoList(config.getId(),ri.getId());
		  String depends = "";
		  String dnew = "";
		  for(BuildConfigInfo bci :list){
			  dnew = "";
			  depends = bci.getBuildDepends();
			  String[] dArray = StringUtil.StringToArray(depends);
			  for(String d:dArray){
				  if(config.getId().equals(d)){
					  continue;
				  }else{
					  if(StringUtil.isBlank_new(dnew)){
						  dnew = d;
					  }else{
						  dnew = dnew + ";" + d;
					  }
				  }
			  }
			  bci.setBuildDepends(dnew);
			  buildConfigInfoDao.saveBuildConfigInfo(bci);
		  }		  
	  }
	  
	  public void saveBcConfigInCancelBuilding(BuildConfig config,String tester){
		  //更新构建包信息为取消构建
		  RepInfo ri = respInfoDao.getRespInfoByName(config.getVersion());
		  BuildConfigInfo bc = buildConfigInfoDao.getBuildConfigInfoByNameNoCancel(config.getId(),ri);
		  if(bc!=null){			 
			  bc.setStatus("4");
			  bc.setBuildDeleter(tester);
			  bc.setBuildDeleteTime(new Date());
			  buildConfigInfoDao.saveBuildConfigInfo(bc);
			  //更新依赖该包的依赖
			  updateDeploys( config, ri);
			  //记录日志
			  BuildConfigInfoLog log = new BuildConfigInfoLog();		 
			  log.setRi(ri);
			  log.setBc(bc);
			  log.setOperater(tester);
			  log.setOperaterCode("4");
			  log.setOperaterName("取消构建");
			  log.setOperaterTime(new Date());
			  buildConfigInfoLogDao.saveBuildConfigInfoLog(log);
		  }
	  }
	  
	  public void saveBcConfigInDeployPacking(BuildConfig config,String deployer) throws Exception{
		  //更新构建包信息为发布
		  RepInfo ri = respInfoDao.getRespInfoByName(config.getVersion());
		  BuildConfigInfo bc = buildConfigInfoDao.getBuildConfigInfoByNameNoCancel(config.getId(),ri);
		  if(bc!=null){		  			  
			  bc.setStatus("5");
			  bc.setDeployUsers(deployer);
			  bc.setDeployTime(new Date());
			  
			  String patchName = "";
			  //判断是否是周BUG线
			  if("1".equals(ri.getIsWeekbug())){
				  patchName = makeWeekBugDeployPackName(ri);
			  }else{
				  //添加补丁记录  如果存在不用添加
				  Date curDate = Calendar.getInstance().getTime();
				  patchName = ri.getBand() +"."+ ri.getVersionNo() +ri.getSpNo()+"." 
						    + (ri.getVersionPattern() == null ? "":new SimpleDateFormat(ri.getVersionPattern()).format(curDate))
						    +"01" +"." + ri.getVersionSuffix();
			  }
			  
			  DeployPackInfo di = deployPackInfoDao.getDeployPackInfoByName(patchName,ri.getId());
			  
			  if(di==null){
				  DeployPackInfo dinew = new DeployPackInfo();
				  dinew.setRi(ri);
				  dinew.setCreateTime(new Date());
				  dinew.setDeployPackName(patchName);
				  deployPackInfoDao.saveDeployPackInfo(dinew);
				  bc.setBd(dinew);
			  }else{
				  di.setCreateTime(new Date());
				  bc.setBd(di);
			  }
			  
			  buildConfigInfoDao.saveBuildConfigInfo(bc);
			  
			  //更新依赖该包的依赖
			  updateDeploys(config, ri);
			  //记录构建包日志
			  BuildConfigInfoLog log = new BuildConfigInfoLog();		 
			  log.setRi(ri);
			  log.setBc(bc);
			  log.setOperater(deployer);
			  log.setOperaterCode("5");
			  log.setOperaterName("发布构建包");
			  log.setOperaterTime(new Date());
			  buildConfigInfoLogDao.saveBuildConfigInfoLog(log);	  
		  }
	  }	 
	  
	  public void saveBcConfigInPrivatePacking(String userName,String branch,String fileName){
		  String buildName = fileName.substring(0,fileName.indexOf(".zip"));
		  RepInfo ri = respInfoDao.getRespInfoByName(branch);
		  BuildConfigInfo bc = buildConfigInfoDao.getBuildConfigInfoByNameNoCancel(buildName,ri);		
		  if(bc!=null){			 
			  PrivatePackLog plog = new PrivatePackLog();
			  plog.setBc(bc);
			  plog.setRi(ri);
			  plog.setDepends(bc.getBuildDepends());
			  plog.setTakePersonName(userName);
			  plog.setTakeTime(new Date());
			  privatePackLogDao.savePrivatePackLog(plog);
		  }
	  }
	  
	  public void saveBcConfigInDeployPacking(String userName,String branch,String fileName){
		  String packName = fileName.substring(0,fileName.indexOf(".zip"));
		  RepInfo ri = respInfoDao.getRespInfoByName(branch);
		  DeployPackInfo di = deployPackInfoDao.getDeployPackInfoByName(packName,ri.getId());	
		  if(di!=null){
			  DeployPackInfoLog dlog = new DeployPackInfoLog();
			  dlog.setDp(di);
			  dlog.setRi(ri);
			  dlog.setTakePersonName(userName);
			  dlog.setTakeTime(new Date());
			  dlog.setTakeType("0");
			  deployPackInfoLogDao.saveDeployPackInfoLog(dlog);
		  }
	  }
	  
	  public void saveBcConfigInEnDeployPacking(String userName,String branch,String fileName){
		  String packName = fileName.substring(0,fileName.indexOf(".zip"));
		  RepInfo ri = respInfoDao.getRespInfoByName(branch);
		  DeployPackInfo di = deployPackInfoDao.getDeployPackInfoByName(packName,ri.getId());
		  if(di!=null){
			  DeployPackInfoLog dlog = new DeployPackInfoLog();
			  dlog.setDp(di);
			  dlog.setRi(ri);
			  dlog.setTakePersonName(userName);
			  dlog.setTakeTime(new Date());
			  dlog.setTakeType("1");
			  deployPackInfoLogDao.saveDeployPackInfoLog(dlog);
		  }
	  }
	  
	  
	  
	  public void delBuildConfigInfo(String id){
		  BuildConfigInfo bc = (BuildConfigInfo)buildConfigInfoDao.getBuildConfigInfo(Long.valueOf(id));	
		  if(bc!=null){
			  buildConfigInfoDao.delBuildConfigInfo(bc);	
		  }
	  }
	  
	//生成/获取周BUG线补丁包名称
	//规则2：    周补丁BUG线：BFS.V10.6.00.分支创建日期 + 02/03/04...(每日最多产生一个)
	private static String makeWeekBugDeployPackName(RepInfo ri)
			throws Exception {

		Date curDate = DateUtil.toDate(ri.getCreateDate(), "yyyyMMdd");// 取分支创建日期
		String dateVersion = (ri.getVersionPattern() == null ? ""
				: new SimpleDateFormat(ri.getVersionPattern()).format(curDate));// 格式化日期版本号

		// 取出最新的补丁包名
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		// 小版本号：02-08
		String smallVersion = (dayOfWeek == Calendar.SUNDAY) ? "08" // 1
				: new DecimalFormat("00").format(dayOfWeek);// 2-7

		String patchName = ri.getBand() + "." + ri.getVersionNo()
				+ ri.getSpNo() + "." + dateVersion + smallVersion + "."
				+ ri.getVersionSuffix();

		return patchName;
	}
}
