package com.easysoft.core.manager.solution.impl;

import com.easysoft.core.ParamSetting;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.manager.solution.InstallUtil;
import com.easysoft.core.model.Site;
import com.easysoft.core.solution.factory.DBSolutionFactory;
import com.easysoft.framework.db.ISqlFileExecutor;
import com.easysoft.framework.db.dbsolution.IDBSolution;
import com.easysoft.framework.db.dbsolution.IInstaller;
import com.easysoft.framework.utils.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 示例数据安装器
 * @author andy
 *
 */
@Service("exampleDataInstaller")
@Scope("prototype")
public class ExampleDataInstaller implements IInstaller {
	private ISqlFileExecutor sqlFileExecutor;
 
	protected final Logger logger = Logger.getLogger(getClass());
	protected String eopsiteSQL;
	
	public String parseConst(String content){
		eopsiteSQL = "";
		Map<String,String> constMap = new HashMap<String,String>();
		char buf[] = new char[content.length()];
		content.getChars(0,content.length(), buf, 0);
		CharArrayReader in = new CharArrayReader(buf);
		BufferedReader reader = new BufferedReader(in);
		while(true){
			try{
				String line = reader.readLine();
				if(line==null)
					break;
				else if(line.startsWith("CONST")){
					String[] value = line.substring(line.indexOf('!')).split("=");
					constMap.put(value[0], value[1]);
					content = content.replaceFirst(line + "\n", "");
				} else if(line.startsWith("EOPSITE")){
					eopsiteSQL = "\n" + line.replaceFirst("EOPSITE", "update eop_site");
					content = content.replaceFirst(line + "\n", "");
				}
				else
					break;
			}catch(Exception e){
				break;
			}
		}
		Set<String> keys = constMap.keySet();
		for (Iterator it = keys.iterator(); it.hasNext();) {
			String key = (String) it.next();
			String value = constMap.get(key);
			content = content.replaceAll(key,value);
			eopsiteSQL = eopsiteSQL.replaceAll(key,value);
		}
		return content;
	}
	
	protected String parseUserSiteID(String content){
		
			content = content.replaceAll("_<userid>","");
			content = content.replaceAll("_<siteid>","");		
			content = content.replaceAll("/user/<userid>/<siteid>","");
			content = content.replaceAll("<userid>","1");
			content = content.replaceAll("<siteid>","1");						
		
		return content;
	}
	
	/**
	 * 使用新的数据库中间件来导入数据
	 * @param productId
	 * @param fragment
	 * @return
	 */
	private void anyDBInstall(String dataSqlPath) throws Exception{
		IDBSolution dbsolution = DBSolutionFactory.getDBSolution();
		dbsolution.setPrefix("t_");
		dbsolution.dbImport(dataSqlPath);
	}

	private String getDataSqlPath(String productId){
		String dataSqlPath = "";
		if(ParamSetting.DBTYPE.equals("1")){
			dataSqlPath = ParamSetting.PRODUCTS_STORAGE_PATH + "/" + productId +"/example_data_mysql.sql";
		}else if (ParamSetting.DBTYPE.equals("2")){
			dataSqlPath = ParamSetting.PRODUCTS_STORAGE_PATH + "/" + productId +"/example_data_oracle.sql";
		}else if (ParamSetting.DBTYPE.equals("3")){
			dataSqlPath = ParamSetting.PRODUCTS_STORAGE_PATH + "/" + productId +"/example_data_mssql.sql";
		}
		return dataSqlPath;
	}
	
	private void loggerPrint(String text){
		if(logger.isDebugEnabled()){
			this.logger.debug(text);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void install(String productId, Node fragment) {
		boolean xmlData = true;
		String dataSqlPath = ParamSetting.PRODUCTS_STORAGE_PATH + "/" + productId +"/example_data.xml";
		try {		
			File xmlFile = new File(dataSqlPath);
			if(!xmlFile.exists()){		//	如果example_data.xml不存在，执行旧版本
				xmlData = false;
				dataSqlPath  = getDataSqlPath(productId);
			}
			
			loggerPrint("安装datasqlPath:" + dataSqlPath);
			
			if(!"base".equals(productId)){
				InstallUtil.putMessaage("正在安装示例数据，可能要花费较长时间，请稍后 <img src='resource/com/enation/app/saas/widget/product/loading.gif'");
			}else{
				InstallUtil.putMessaage("正在安装基础数据...");
			}
			
			if(xmlData){
				anyDBInstall(dataSqlPath);
			} else {
				if(new File(dataSqlPath).exists()){		
					//安装示例数据
					String content = FileUtil.read(dataSqlPath, "UTF-8");
					//解析文件头部CONST和EOPSITE命令，CONST用于替换下面的文本内容，EOPSITE用于更新eop_site表
					content = parseConst(content);
					
					content = parseUserSiteID(content);
					content = this.filter(content);
					content = content + parseUserSiteID(eopsiteSQL);
					
					sqlFileExecutor.execute(content);
					
				} else {
					System.out.println(dataSqlPath+" not exist");
				}
			}
			loggerPrint("示例数据安装完毕");
			
			FileUtil.copyFolder( 
					ParamSetting.PRODUCTS_STORAGE_PATH + "/" + productId + "/attachment/",
					ParamSetting.IMG_SERVER_PATH + EsfContext.getContext().getContextPath()+ "/attachment/");
		 
			//非base应用copy init.sql
			if(!"base".equals(productId)){
				FileUtil.copyFile(ParamSetting.PRODUCTS_STORAGE_PATH + "/" + productId + "/init.sql", ParamSetting.ESF_PATH + EsfContext.getContext().getContextPath() + "/init.sql");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.debug(e.getMessage(),e);
			throw new RuntimeException("安装示例数据出错...");
		}		 
	}
	
	/**
	 * 过滤drop/truncate/delete语句/以及eop_开头的语句
	 * @param input
	 * @return
	 */
	private String filter(String input){
		Pattern pattern = Pattern.compile("delete\\s?.*?\\s?;", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		input = matcher.replaceAll("");
		pattern = Pattern.compile("truncate\\s?.*?\\s?;", Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(input);
		input = matcher.replaceAll("");
		pattern = Pattern.compile("drop\\s?.*?\\s?;", Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(input);
		input = matcher.replaceAll("");
		pattern = Pattern.compile("(delete|drop|truncate|insert|update)\\s?eop_.*?;", Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(input);
		input = matcher.replaceAll("");

		return input;
	}
	
	public void setSqlFileExecutor(ISqlFileExecutor sqlFileExecutor) {
		this.sqlFileExecutor = sqlFileExecutor;
	}
	
	public static void main(String[] args){
		String content = FileUtil.read("D:/Works/Trunk/javamall/WebContent/products/eopsaler/example_data_mysql.sql", "UTF-8");
		//content = ExampleDataInstaller.parseConst(content);
		System.out.println(content.substring(0,50));
	}

}
