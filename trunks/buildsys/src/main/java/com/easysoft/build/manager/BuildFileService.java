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



import com.easysoft.build.cache.BPICache;
import com.easysoft.build.model.BuildConfig;
import com.easysoft.build.model.BuildFile;
import com.easysoft.build.model.RepositoryInfo;
import com.easysoft.build.utils.AntTaskUtil;
import com.easysoft.build.utils.BuildLogger;
import com.easysoft.build.utils.PatchUtil;
import com.easysoft.framework.utils.DateUtil;
import org.apache.tools.ant.util.FileUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 构建包相关服务
 * @author pangl
 *
 */
public abstract class BuildFileService {
	
	/**
	 * 最后的补丁包名称
	 */
	public static final String LATEST_PATCH_NAME = "latest.zip";
	
	/**
	 * 获取指定分支上的一个BuildPackInfo对象
	 * @param branchName
	 * @param fileName 不带路径的文件名称
	 * @return
	 */
	public static BuildFile getBuildPackInfo(String branchName, String fileName) {
		fileName = fileName.trim();
		if (fileName.length() == 0)
			return null;
		if (!fileName.endsWith(".zip"))
			fileName = fileName + ".zip";
		RepositoryInfo info = BuildReposManager.getByName(branchName);
		File file = new File(info.getBuildDir(), fileName);
		return BPICache.getBuildPackInfo(branchName, file);
	}
	
	/**
	 * 获取指定补丁中包含的BuilPackInfo对象
	 * @param branchName
	 * @param patchName
	 * @param fileName
	 * @return
	 */
	public static BuildFile getBuildPackInfo(String branchName, File file) {
		return BPICache.getBuildPackInfo(branchName, file);
	}

	/**
	 * 判断某个构建包是否可以发布
	 * @param config 构建包信息
	 * @return
	 */
	public static void checkCanDeploy(BuildFile buildFile) throws Exception{
		List<BuildFile> allBuildFiles = new ArrayList<BuildFile>();
		retriveDepends(buildFile, null, allBuildFiles);
		for (int i=allBuildFiles.size()-2; i>=0; i--) {
			BuildFile bf = allBuildFiles.get(i);
			if (bf.getFile().exists() && bf.getConfig().getDeployTS() <= 0) {
				throw new Exception("依赖的包未发布：" + bf.getFileName());
			}
		}
	}
	
	/**
	 * 发布补丁包
	 * @param branch
	 * @param id
	 * @throws Exception
	 */
	public static void deployPack(BuildFile buildFile) throws Exception {
		BuildConfig config = buildFile.getConfig();
		String id = config.getId();
		RepositoryInfo reposInfo = BuildReposManager.getByName(buildFile.getBranchName());
		BuildLogger logger = new BuildLogger(reposInfo, id + "_deploy");
		logger.startBuild();
		try {
			checkCanDeploy(buildFile);

			String patchName = "";
			Date curDate = null;
			if(reposInfo.isWeekbug()){//周BUG线
				curDate = DateUtil.toDate(reposInfo.getCreateDate(), "yyyyMMdd");// 取分支创建日期;
				patchName = makeWeekBugDeployPackName(reposInfo);
			}else{//非周BUG线
				curDate = Calendar.getInstance().getTime();
				patchName = reposInfo.getBand()+"."+reposInfo.getVersionNo() +reposInfo.getSpNo()+"."+
						(reposInfo.getVersionPattern() == null ? "" : 
							new SimpleDateFormat(reposInfo.getVersionPattern()).format(curDate))+"01" +"."+
							reposInfo.getVersionSuffix();
			}
			//合并构建包及其依赖的包到补丁当中
			
			File patchFile = new File(reposInfo.getDeployDir(), patchName + ".zip");
			buildPatchFile(buildFile, patchFile, logger);
			File latestFile = new File(reposInfo.getDeployDir(), LATEST_PATCH_NAME);
			FileUtils.getFileUtils().copyFile(patchFile, latestFile);
			
			//拷贝最新的class依赖
			File temp = new File(reposInfo.getWorkspace(), "temp/latest");
			AntTaskUtil.emptyDir(logger, temp);
			AntTaskUtil.unzip(latestFile, temp, logger);
			AntTaskUtil.copyFiles(new File(temp, "/web/WEB-INF/classes"),
                    reposInfo.getCompileClassDir(), "**/*.class", logger);
			
			//备份并移除已发布的包
			List<BuildFile> allBuildFiles = new ArrayList<BuildFile>();
			retriveDepends(buildFile, null, allBuildFiles);
			String path = PatchUtil.getBackupDir(curDate, reposInfo.isWeekbug());
			
			//修改发布时间
			for (int i=allBuildFiles.size()-1; i>=0; i--) {
				BuildFile bf = allBuildFiles.get(i);
				BuildConfig cfg = bf.getConfig();
				cfg.setDeployTS(System.currentTimeMillis());
				bf.storeAndWait2();				
				//备份文件并移除相关信息
				File backFile = new File(reposInfo.getDeployBackupDir(), path + cfg.getId() + ".zip");
				FileUtils.getFileUtils().copyFile(bf.getFile(), backFile);
				bf.getFile().delete();
				removeDepend(bf);
				BPICache.remove(bf);
			}
		} catch (Exception ex) {
			logger.logMessage(ex.getMessage());
			throw ex;
		} finally {
			logger.endBuild();
		}
	}
	
	/**
	 * 去掉依赖信息，只对已构建未发布的构建包有效
	 * @param branch
	 * @param dependId
	 */
	public static void removeDepend(BuildFile dependedInfo) {
		BuildFile[] infos = listBildPackInfo(dependedInfo.getBranchName());
		String id = dependedInfo.getConfig().getId();
		for (BuildFile info : infos) {
			BuildConfig conf = info.getConfig();
			if (conf.removeDepend(id)) {
				info.storeConfig();
			}
		}
	}
	
	/**
	 * 开始测试某个包
	 * @param info
	 * @param user
	 */
	public static void startTest(BuildFile info, String user) {
		info.getConfig().setTesters(user);
	}
	
	/**
	 * 取消测试
	 * @param info
	 */
	public static void cancelTest(BuildFile info) {
		info.getConfig().setTesters(null);
	}
	
	/**
	 * 查找包含某个文件的构建包
	 * @param branch
	 * @param filePattern
	 * @return
	 */
	public static BuildFile[] findBPIByFile(String branch, String filePattern) {
		BuildFile[] infos = listBildPackInfo(branch);
		List<BuildFile> list = new ArrayList<BuildFile>();
		for (BuildFile info : infos) {
			String[] allFs = info.getConfig().getAllFiles();
			for (String f : allFs) {
				if (f.indexOf(filePattern) != -1) {
					list.add(info);
					break;
				}
			}
		}
		return (BuildFile[])list.toArray(new BuildFile[list.size()]);
	}
	
	/**
	 * 列出所有已构建的包信息
	 * @return 不存在则返回长度为0的数组对象
	 */
	public static BuildFile[] listBildPackInfo(String branch) {
		
		RepositoryInfo repos = BuildReposManager.getByName(branch);
		TreeSet<BuildFile> bpInfos = new TreeSet<BuildFile>();
		File root = repos.getBuildDir();
		//System.out.println("List from " + root);
		File[] zips = root.listFiles();
		if (zips == null  || zips.length == 0) {
			//System.out.println("[" + branch + "] No build package in dir:" + root);
			return new BuildFile[0];
		}
		for (File zip : zips) {
			if (zip.getName().endsWith(".zip")) {
				try {
					BuildFile info = getBuildPackInfo(branch, zip.getName());
					if (info != null)
						bpInfos.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return (BuildFile[])bpInfos.toArray(new BuildFile[bpInfos.size()]);
	}
	
	/**
	 * 获取所有已构建但未发布的构建包中包含的文件列表
	 * @param branch
	 * @return
	 */
	public static Set<String> listAllTestingFile(String branch) {
		BuildFile[] infos = listBildPackInfo(branch);
		Set<String> allFiles = new HashSet<String>();
		for (BuildFile info : infos) {
			String[] files = info.getConfig().getAllFiles();
			for (String f : files) {
				allFiles.add(f);
			}
		}
		return allFiles;
	}
	
	/**
	 * 生成私家包并返回文件路径
	 * @param branchName
	 * @param file
	 * @return
	 * @throws java.io.IOException
	 */
	public static File getPrivateFile(String branchName, String file) throws IOException  {
		RepositoryInfo repos = BuildReposManager.getByName(branchName);
		File latestPatch = new File(repos.getDeployDir(), LATEST_PATCH_NAME);
		BuildFile buildFile = getBuildPackInfo(branchName, file);
		File targetFile = new File(repos.getPrivateDir(), "private_" + buildFile.getFileName());
		//如果私家已存在并且是最新的则直接返回
		if (targetFile.exists() &&
				targetFile.lastModified() > buildFile.getFile().lastModified() &&
				targetFile.lastModified() > latestPatch.lastModified()) {
			return targetFile;
		}

		buildPatchFile(buildFile, targetFile, null);
		return targetFile;
	}

	/**
	 * 合并SQL文件
	 * @param repos
	 * @param patchDir
	 * @param buildDir
	 * @param patchName
	 * @param logger
	 * @throws java.io.IOException
	 */
	private static void mergeSqls(RepositoryInfo repos, File patchDir, File buildDir, String patchName, BuildLogger logger) throws IOException {
		logger.logMessage("合并SQL ===============================");
		for (String sqlSufix : repos.getSqlSuffixs()) {
			final String suffix = sqlSufix;
			final StringBuilder sb = new StringBuilder();
			File[] vpSqls = new File(buildDir, "/sql").listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					if (name.endsWith(suffix)) {
						sb.append(name + ";");
						return true;
					}
					return false;
				}
			});
			if (vpSqls == null || vpSqls.length == 0)
				continue;
			File patchSql = new File(patchDir, "sql/" + patchName + sqlSufix);
			logger.logMessage("合并SQL文件：" + sb.toString());
			PatchUtil.mergeSqlTo(vpSqls, patchSql, repos.getSqlEncoding());
		}
	}

	private static void mergeReadme(File patchTemp, List<BuildFile> buildFiles, BuildLogger logger) throws IOException {
		File readmeFile = new File(patchTemp, "readme.txt");
		logger.logMessage("合并ReadMe文件：" + readmeFile + "===========================");
		StringBuilder sb = new StringBuilder();
		if (readmeFile.exists()) {
			sb = PatchUtil.readFile(readmeFile, "GBK");
		}
		for (BuildFile bf : buildFiles) {
			BuildConfig config = bf.getConfig();
			PatchUtil.replaceSection(sb, config.getId(), config.getVps() + "\r\n" + config.getComment());
			PatchUtil.write2File(sb.toString(), readmeFile);
		}
	}

	/**
	 * 构建补丁包或私家包
	 * @param branchName 分支名称
	 * @param buildFileName 构建包名称
	 * @param patchFile 目标文件
	 * @param logger 日志输出对象
	 * @throws java.io.IOException
	 */
	private static void buildPatchFile(BuildFile buildFile, File patchFile, BuildLogger logger) throws IOException {
		String patchName = patchFile.getName();
		if (patchName.endsWith(".zip"))
			patchName = patchName.substring(0, patchName.length() - ".zip".length());
		RepositoryInfo repos = BuildReposManager.getByName(buildFile.getBranchName());
		boolean newLogger = logger == null;
		if (newLogger) {
			logger = new BuildLogger(repos, patchName + "build.log");
			logger.startBuild();
		}
		
		File patchTemp = new File(repos.getWorkspace(), "temp/" + patchName);
		File buildTemp = new File(repos.getWorkspace(), "temp/" + patchName + "_build");
		AntTaskUtil.emptyDir(logger, patchTemp, buildTemp);
		
		logger.logMessage("拷贝前一次补丁内容===========================");
		File latestFile = new File(repos.getDeployDir(), LATEST_PATCH_NAME);
		AntTaskUtil.unzip(latestFile, patchTemp, logger);
		
		logger.logMessage("解压构建包补丁内容 ===============================");
		List<BuildFile> allBuildFiles = new ArrayList<BuildFile>();
		retriveDepends(buildFile, null, allBuildFiles);
		for (int i=allBuildFiles.size()-1; i>=0; i--) {
			BuildFile bf = allBuildFiles.get(i);
			AntTaskUtil.unzip(bf.getFile(), buildTemp, logger);
		}
		
		logger.logMessage("拷贝构建包内容===========================");
		AntTaskUtil.copyFiles(buildTemp, patchTemp, "web/**/*.*", logger);
		AntTaskUtil.copyFiles(buildTemp, patchTemp, "cs/**/*.*", logger);
		
		mergeSqls(repos, patchTemp, buildTemp, patchName, logger);
		mergeReadme(patchTemp, allBuildFiles, logger);

		logger.logMessage("写入版本信息 " + repos.getVersionInfo() + "===========================");
		StringBuilder versionInfo = PatchUtil.readFile(repos.getVersionInfo(), "UTF-8");
		int start = versionInfo.indexOf("<bs_ver>");
		int end = versionInfo.indexOf("</bs_ver>");
		versionInfo.replace(start+"<bs_ver>".length(), end, patchName);
		
		start = versionInfo.indexOf("<band>");
		end = versionInfo.indexOf("</band>");
		versionInfo.replace(start+"<band>".length(), end, repos.getBand());
		
		start = versionInfo.indexOf("<permit_band>");
		end = versionInfo.indexOf("</permit_band>");
		versionInfo.replace(start+"<permit_band>".length(), end, repos.getPermitBand());
		
		start = versionInfo.indexOf("<permit_patch>");
		end = versionInfo.indexOf("</permit_patch>");
		versionInfo.replace(start+"<permit_patch>".length(), end, repos.getPermitPatch());
		
		start = versionInfo.indexOf("<version_suffix>");
		end = versionInfo.indexOf("</version_suffix>");
		versionInfo.replace(start+"<version_suffix>".length(), end, repos.getVersionSuffix());
		
		start = versionInfo.indexOf("<create_date>");
		end = versionInfo.indexOf("</create_date>");
		versionInfo.replace(start+"<create_date>".length(), end, repos.getCreateDate());
		
		start = versionInfo.indexOf("<main_version>");
		end = versionInfo.indexOf("</main_version>");
		versionInfo.replace(start+"<main_version>".length(), end, repos.getMainVersion());
		
		start = versionInfo.indexOf("<sub_version>");
		end = versionInfo.indexOf("</sub_version>");
		versionInfo.replace(start+"<sub_version>".length(), end, repos.getSubVersion());
		
		start = versionInfo.indexOf("<sp_no>");
		end = versionInfo.indexOf("</sp_no>");
		versionInfo.replace(start+"<sp_no>".length(), end, repos.getSpNo());
		
		PatchUtil.write2File(versionInfo.toString(), new File(patchTemp, "web/WEB-INF/versioninfo.xml"));
		
		//重新压缩到补丁
		logger.logMessage("重新压缩补丁 ===========================");
		AntTaskUtil.zip(patchTemp, patchFile, logger);

		logger.logMessage("清除临时文件===========================");
		AntTaskUtil.deleteDir(buildTemp, logger);
		AntTaskUtil.deleteDir(patchTemp, logger);
		if (newLogger)
			logger.endBuild();
	}
	
	/**
	 * 获取所有依赖的构建包对象，被依赖的构建包放在列表后面
	 * @param buildFile 
	 * @param dependList 被依赖的构建包ID列表，初始时为null
	 * @param allBuildFiles 所有构建包列表，不能为null，初始时传一个长度为0的List<BuildFile>对象
	 */
	private static void retriveDepends(BuildFile buildFile, List<String> dependList, List<BuildFile> allBuildFiles) {
		if (dependList == null)
			dependList = new ArrayList<String>();
		if (dependList.size() == 0)
			dependList.add(buildFile.getConfig().getId());
		if (allBuildFiles.size() == 0)
			allBuildFiles.add(buildFile);
		
		String[] depends = buildFile.getConfig().getDepends();
		if (depends != null) {
			for (String depend : depends) {
				BuildFile depFile = BuildFileService.getBuildPackInfo(buildFile.getBranchName(), depend);
				if (depFile == null) 
					continue;
				String id = depFile.getConfig().getId();
				//被依赖的包移到最后
				if (dependList.contains(id)) {
					int index = dependList.indexOf(id);
					dependList.remove(index);
					allBuildFiles.remove(index);
				}
				dependList.add(id);
				allBuildFiles.add(depFile);
				retriveDepends(depFile, dependList, allBuildFiles);
			}
		}
	}
	
	//生成/获取周BUG线补丁包名称
	//规则2：    周补丁BUG线：BFS.V10.6.00.分支创建日期 + 02/03/04...(每日最多产生一个)
	private static String makeWeekBugDeployPackName(RepositoryInfo ri)
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
