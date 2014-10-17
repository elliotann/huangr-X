package com.easysoft.build.vo;


/**
 * 客户端下载的文件类型
 * @author pangl
 *
 */
public enum DownloadFileType {
	/**
	 * 构建包文件
	 */
	build,
	
	/**
	 * 补丁文件
	 */
	patch,
	
	/**
	 * 日志文件
	 */
	log,
	
	/** 
	 * 加密补丁包
	 */
	epatch,
	
	/**
	 * 补丁包内文件
	 */
	dbuild,
	
	/**
	 * 私家包 
	 */
	pbuild,
	
	/**
	 * 补丁日志
	 */
	bdlog,
	/**
	 * 批量下载
	 */
	dispatchdd,
}
