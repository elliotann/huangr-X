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

package com.easysoft.build.utils;

import com.easysoft.build.cfg.SysConfiguration;
import com.easysoft.build.manager.BuildFileService;
import com.easysoft.build.manager.BuildReposManager;
import com.easysoft.build.model.BuildConfig;
import com.easysoft.build.model.BuildFile;
import com.easysoft.build.model.RepositoryInfo;
import com.easysoft.build.web.WebAppManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 补丁发布及构建工具类
 * @author pangl
 *
 */
public abstract class PatchUtil {
	
	
	/**
	 * 返回补丁构建的备份目录
	 * @param date
	 * @return
	 */
	public static String getBackupDir(Date date,boolean isWeekbug) {
		if(isWeekbug){//周BUG线
			Calendar cal = Calendar.getInstance();
			String dir = new SimpleDateFormat("yyyyMMdd/").format(date);
			
			if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){//周日为08
				dir += "08/";
			}else{//02-07
				dir += new DecimalFormat("00/").format(cal.get(Calendar.DAY_OF_WEEK));
			}
			return dir;
		}else{
			return new SimpleDateFormat("yyyy/MM/dd/").format(date);
		}
	}
	
	/**
	 * 构建补丁包
	 */
	public static void buildPackage(BuildConfig config) throws Exception{
		
		RepositoryInfo repos = BuildReposManager.getByName(config.getVersion());
		BuildLogger logger = new BuildLogger(repos, config.getId());
		logger.startBuild();
		try {
			String id = config.getId();
			String zipName = id + ".zip";			
			File buildFile = new File(repos.getBuildDir(), zipName);
			//源文件
			String zipSrcName = id + ".zip";
			File buildSrcFile = new File(repos.getBuildSrcDir(), zipSrcName);
			
			if (buildFile.isFile() && buildFile.exists()) {
				logger.logMessage("存在同名待测试构建包，请先取消测试再进行构建：" +zipName);
				throw new Exception("存在待测试构建包，请先取消测试再进行构建：" +zipName);
			}
			
			//---????
			BuildFile[] infos = BuildFileService.listBildPackInfo(config.getVersion());
			String[] bFiles = config.getAllFiles();
			for (BuildFile info : infos) {
				BuildConfig bConfig = info.getConfig();
				for (String bf : bFiles) {
					if (bConfig.containsFile(bf)) {
						config.addDepend(bConfig.getId());
						logger.logMessage("文件" + bf + "已存在构建包：" + bConfig.getId() + ".zip 当中\n");
					}
				}
			}
			
			SVNUtil.checkOutModule(repos.getName(), "sql", logger); //检出sql信息
			String[] modules = config.getModules().split(";");
			for (String module : modules) {
				if (module.trim().length() > 0)
					SVNUtil.checkOutModule(repos.getName(), module, logger);//检出对应的工程
			}
			logger.logMessage("删除临时文件===========================");
			AntTaskUtil.deleteDir(getTempDir(config), logger);
			logger.logMessage("编译Java============================");
			compileJava(config, logger);
			logger.logMessage("开始打包=============================");
			copy4BuildPack(config, logger);
			
			logger.logMessage("写入配置信息===========================");
			config.setBuildTS(System.currentTimeMillis());
			write2File(config.toXML(), new File(getPackDir(config), config.getId() + ".xml"));
			
			//打包ZIP文件
			AntTaskUtil.zip(getPackDir(config), buildFile, logger);
			logger.logMessage("打包完成：" + buildFile);
			
			//开始打源文件包
			logger.logMessage("开始打包=============================");
			copy4BuildSrcPack(config, logger);
			logger.logMessage("写入配置信息===========================");			
			write2File(config.toXML(), new File(getPackSrcDir(config), config.getId() + ".xml"));
			//打包ZIP文件
			AntTaskUtil.zip(getPackSrcDir(config), buildSrcFile, logger);
			logger.logMessage("打包完成：" + buildSrcFile);
			
			//请空临时目录
			AntTaskUtil.deleteDir(getTempDir(config), logger);
		} finally {
			logger.endBuild();
		}
	}
	
	/**
	 * 判断某个构建包是否可以发布
	 * @param config 构建包信息
	 * @return
	 */
	public static void checkCanDeploy(BuildConfig config) throws Exception{
		RepositoryInfo reposInfo = BuildReposManager.getByName(config.getVersion());
		File root = reposInfo.getBuildDir();
		String[] deps = config.getDepends();
		for (String dep : deps) {
			if (new File(root, dep + ".zip").exists()) {
				throw new Exception("依赖的包未发布：" + dep);
			}
		}
	}
	
	/**
	 * 写文件内容到指定文件
	 * @param content 文本内容
	 * @param dest 目标文件
	 * @throws java.io.IOException
	 */
	public static void write2File(String content, File dest) throws IOException {
		byte[] bs = content.getBytes("GBK");
		FileOutputStream out = new FileOutputStream(dest);
		out.write(bs);
		out.flush();
		out.close();
	}

	/**
	 * 替换指字符串中的某一段
	 * @param sb 目标字符串对象
	 * @param sectionName 段的名称，将作为查找的依据
	 * @param content 段的内容， 如果内容为空则只进行删除操作
	 */
	public static void replaceSection(StringBuilder sb, String sectionName, String content) {
		String prefix = "\r\n-- -" + sectionName + "-\r\n";
		String suffix = "\r\n-- =" + sectionName + "=\r\n";
		int sIndex = sb.indexOf(prefix);
		int eIndex = sb.indexOf(suffix);
		if (sIndex != -1 && eIndex != -1 && sIndex < eIndex) {
			sb.replace(sIndex, eIndex + suffix.length(), "");
		}
		if (content.length() > 0) {
			sb.append(prefix);
			content = content.replaceAll("，", ",").replaceAll("；", ";").replaceAll("‘", "'").replaceAll("’", "'").replaceAll("。", ".");
			sb.append(content);
			sb.append(suffix);
		}


	}

	/**
	 * 合并SQL文件
	 * @param vpSqls 合并源文件列表，这些文件中的内容将被合将到patchFile文件当中
	 * @param patchFile 合并目标文件，合并之后的内容是原内容加上新增的内容
	 * @param encoding 指定文件的编码
	 * @throws java.io.IOException
	 */
	public static void mergeSqlTo(File[] vpSqls, File patchFile, String encoding) throws IOException {
		if (encoding == null)
			encoding = "UTF-8";
		StringBuilder sb = readFile(patchFile, encoding);
		for (File sqlFile : vpSqls) {
			StringBuilder sql = readFile(sqlFile, encoding);
			replaceSection(sb, sqlFile.getName(), sql.toString());
		}
		patchFile.getParentFile().mkdirs();
		FileOutputStream out = new FileOutputStream(patchFile);
		out.write(sb.toString().getBytes(encoding));
		out.flush();
		out.close();
	}

	/**
	 * 读取SQL文件内容
	 * @param sqlFile
	 * @return
	 * @throws java.io.IOException
	 */
	public static StringBuilder readFile(File file, String encoding) throws IOException {
		if (encoding == null)
			encoding = "UTF-8";
		StringBuilder sb = new StringBuilder();
		if (!file.exists() || !file.isFile())
			return sb;
		final int buffer = 1024;
		byte[] bs = new byte[buffer];
		FileInputStream in = new FileInputStream(file);
		int len = in.read(bs);
		int total = 0;
		while(len != -1) {
			total += len;
			byte[] tmp = new byte[total+buffer];
			System.arraycopy(bs, 0, tmp, 0, total);
			bs = tmp;
			len = in.read(bs, total, buffer);
		}
		sb.append(new String(bs, 0, total, encoding));
		in.close();
		return sb;
	}

	/**
	 * 拷贝构建包文件
	 * @param config
	 * @throws java.io.IOException
	 */
	private static void copy4BuildPack(BuildConfig config, BuildLogger logger) throws IOException {

		File packDir = getPackDir(config);
		doCopy(config, config.getWebFileList(), "/src/main/webapp/", new File(packDir, "web"), logger);
		doCopy(config, config.getCsFileList(), "/cs/", new File(packDir, "cs"), logger);
		doCopy(config, config.getResourceFileList(), "/src/main/resources/", new File(packDir, "web/WEB-INF/classes"), logger);

		RepositoryInfo repos = BuildReposManager.getByName(config.getVersion());
		String[] sqlSuffix = repos.getSqlSuffixs();
		String sqlReposRoot = repos.getSqlRepos();
		String[] vps = config.getVps().split(";");
		List<String> files = new ArrayList<String>();
		for (String vp : vps) {
			vp = vp.trim();
			if (vp.length() == 0)
				continue;
			for(String suf : sqlSuffix) {
				files.add(sqlReposRoot + vp + suf);
			}
		}
		doSqlCopy(config, (String[])files.toArray(new String[files.size()]), "/sql/patch/", new File(packDir, "sql"), logger);
	}

	/**
	 * 拷贝构建包源文件
	 * @param config
	 * @throws java.io.IOException
	 */
	private static void copy4BuildSrcPack(BuildConfig config, BuildLogger logger) throws IOException {

		File packSrcDir = getPackSrcDir(config);
		doSrcCopy(config, config.getJavaFileList(), "/src/main/java/", new File(packSrcDir, "src"), logger);
		doSrcCopy(config, config.getWebFileList(), "/src/main/webapp/", new File(packSrcDir, "web"), logger);
		doCopy(config, config.getCsFileList(), "/cs/", new File(packSrcDir, "cs"), logger);
		doSrcCopy(config, config.getResourceFileList(), "/src/main/resources/", new File(packSrcDir, "web/WEB-INF/classes"), logger);

		RepositoryInfo repos = BuildReposManager.getByName(config.getVersion());
		String[] sqlSuffix = repos.getSqlSuffixs();
		String sqlReposRoot = repos.getSqlRepos();
		String[] vps = config.getVps().split(";");
		List<String> files = new ArrayList<String>();
		for (String vp : vps) {
			vp = vp.trim();
			if (vp.length() == 0)
				continue;
			for(String suf : sqlSuffix) {
				files.add(sqlReposRoot + vp + suf);
			}
		}
		doCopy(config, (String[])files.toArray(new String[files.size()]), "/sql/patch/", new File(packSrcDir, "sql"), logger);
	}

	/**
	 * 从SVN的本地目录中拷贝文件到指定目录
	 * @param config
	 * @param files
	 * @param subDir
	 * @param destDir
	 * @throws java.io.IOException
	 */
	private static void doCopy(BuildConfig config, String[] files, String subDir, File destDir, BuildLogger logger) throws IOException {
		if (files == null || files.length == 0)
			return;
		if (!destDir.exists())
			destDir.mkdirs();
		subDir = subDir.replace("\\\\", "/").trim();
		if (subDir.length() > 0) {
			if (!subDir.startsWith("/"))
				subDir = "/" + subDir;
			if (!subDir.endsWith("/"))
				subDir = subDir + "/";
		}

		RepositoryInfo repos = BuildReposManager.getByName(config.getVersion());
		File svnRoot = repos.getSvnWorkspace();
		FileUtils fileUtil = FileUtils.getFileUtils();
		for (String file : files) {
			File srcFile = new File(svnRoot, file);
			if (!srcFile.exists()) {
				logger.logMessage("文件不存在:" + srcFile);
				continue;
			}
			String path = srcFile.getAbsolutePath();
			path = path.replaceAll("\\\\", "/");
			int index = path.indexOf(subDir);
			path = path.substring(index + subDir.length());
			File destFile = new File(destDir, path);
			fileUtil.copyFile(srcFile, destFile);
			logger.logMessage("copyFile:[" + srcFile + " ] to [" + destFile + "]");
		}
	}

	/**
	 * 从SVN的本地目录中拷贝文件到指定目录
	 * @param config
	 * @param files
	 * @param subDir
	 * @param destDir
	 * @throws java.io.IOException
	 */
	private static void doSqlCopy(BuildConfig config, String[] files, String subDir, File destDir, BuildLogger logger) throws IOException {
		if (files == null || files.length == 0)
			return;
		if (!destDir.exists())
			destDir.mkdirs();
		subDir = subDir.replace("\\\\", "/").trim();
		if (subDir.length() > 0) {
			if (!subDir.startsWith("/"))
				subDir = "/" + subDir;
			if (!subDir.endsWith("/"))
				subDir = subDir + "/";
		}

		RepositoryInfo repos = BuildReposManager.getByName(config.getVersion());
		File svnRoot = repos.getSvnWorkspace();
		FileUtils fileUtil = FileUtils.getFileUtils();
		boolean sqlbool = false;
		String sqlFiles = "";
		for (String file : files) {
			File srcFile = new File(svnRoot, file);
			if (!srcFile.exists()) {
				logger.logMessage("文件不存在:" + srcFile);
				continue;
			}
			String path = srcFile.getAbsolutePath();
			path = path.replaceAll("\\\\", "/");
			int index = path.indexOf(subDir);
			path = path.substring(index + subDir.length());
			File destFile = new File(destDir, path);
			fileUtil.copyFile(srcFile, destFile);
			logger.logMessage("copyFile:[" + srcFile + " ] to [" + destFile + "]");
			sqlbool = true;
			if(StringUtils.isEmpty(sqlFiles)){
				sqlFiles = file;
			}else{
				sqlFiles+=";"+file;
			}
		}
		if(sqlbool){
			config.setHas_sql("1");
			config.setSqlFiles(sqlFiles);
		}else{
			config.setHas_sql("0");
			config.setSqlFiles(sqlFiles);
		}
	}

	private static void doSrcCopy(BuildConfig config, String[] files, String subDir, File destDir, BuildLogger logger) throws IOException {
		if (files == null || files.length == 0)
			return;
		if (!destDir.exists())
			destDir.mkdirs();
		subDir = subDir.replace("\\\\", "/").trim();
		if (subDir.length() > 0) {
			if (!subDir.startsWith("/"))
				subDir = "/" + subDir;
			if (!subDir.endsWith("/"))
				subDir = subDir + "/";
		}

		RepositoryInfo repos = BuildReposManager.getByName(config.getVersion());
		File svnRoot = repos.getSvnWorkspace();
		FileUtils fileUtil = FileUtils.getFileUtils();
		for (String file : files) {
			File srcFile = new File(svnRoot, file);
			if (!srcFile.exists()) {
				logger.logMessage("文件不存在:" + srcFile);
				continue;
			}
			String path = srcFile.getAbsolutePath();
			String module = "";
			path = path.replaceAll("\\\\", "/");
			int index = path.indexOf(subDir);
			module = path.substring(0,index);
			module = module.substring(module.lastIndexOf("/"),module.length());
			path = path.substring(index + subDir.length());
			File destFile = new File(destDir, module+subDir+path);
			fileUtil.copyFile(srcFile, destFile);
			logger.logMessage("copyFile:[" + srcFile + " ] to [" + destFile + "]");
		}
	}


	/**
	 * 获取构建对应的临时目录
	 * @param config
	 * @return
	 */
	private static File getTempDir(BuildConfig config) {
		RepositoryInfo repos = BuildReposManager.getByName(config.getVersion());
		File dir =  new File(repos.getWorkspace(), "temp/" + config.getId());
		if (!dir.exists())
			dir.mkdirs();
		return dir;
	}

	private static File getPackDir(BuildConfig config) {
		File dir =   new File((getTempDir(config)), "pack");
		if (!dir.exists())
			dir.mkdirs();
		return dir;
	}

	private static File getPackSrcDir(BuildConfig config) {
		File dir =   new File((getTempDir(config)), "packSrc");
		if (!dir.exists())
			dir.mkdirs();
		return dir;
	}

	/**
	 * 执行拷贝并javac编译
	 * @param config
	 * @throws java.io.IOException
	 */
	private static void compileJava(BuildConfig config, BuildLogger logger) throws IOException {
		
		if (config.getJavaFileList().length == 0)
			return;
		RepositoryInfo repos = BuildReposManager.getByName(config.getVersion());
		File tempDir = getTempDir(config);
		
		File src = new File(tempDir, "javaSrc");
		doCopy(config, config.getJavaFileList(), "/src/main/java/", src, logger);  //在这里可以添加代码备份每次构建对应的源代码
		
		File complieDest = new File(getPackDir(config), "web/WEB-INF/classes/");
		complieDest.mkdirs();

		Javac javac = new Javac();
		javac.setProject(new Project());
		javac.setDestdir(complieDest);
		javac.setIncludeantruntime(true);
		javac.setIncludejavaruntime(true);
		javac.setFailonerror(true);
		javac.setVerbose(true);
		javac.setDebug(true);
		javac.setDebugLevel("lines,vars,source");
		
		//根据分支配置，指定JDK编译器和JDK版本，指定编码格式
		//hush20140221
		javac.setEncoding(repos.getSrcEncoding());
		javac.setSource(repos.getJdkVersion());
		javac.setTarget(repos.getJdkVersion());

		//根据分支配置的jdk版本，从系统配置中获取指定jdk版本的编译器path
		SysConfiguration cfg = WebAppManager.instance().getSysConfiguration();
		String executable = cfg.getJavacPath(repos.getJdkVersion());
		if(null != executable){
			javac.setExecutable(executable);
			javac.setFork(true);//与setExecutable同时使用
		}
		
		Path srcPath = new Path(javac.getProject());
		srcPath.createPathElement().setLocation(src);
		javac.setSrcdir(srcPath);
		
		Path libPath = new Path(javac.getProject());
		libPath.createPathElement().setLocation(repos.getCompileClassDir());
		FileSet fs = new FileSet();
		fs.setDir(repos.getCompileLibDir());
		libPath.addFileset(fs);
		javac.setClasspath(libPath);
		
		javac.getProject().setBaseDir(tempDir);
		if (logger != null)
			javac.getProject().addBuildListener(logger);
		javac.execute();
	}
}
