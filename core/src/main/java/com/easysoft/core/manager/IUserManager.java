package com.easysoft.core.manager;

import com.easysoft.core.model.JEAPUser;
import com.easysoft.framework.db.Page;

/**
 * @author andy
 * @since: 1.0
 */
public interface IUserManager {
	
	String USER_SESSION_KEY ="eop_user_key";
	
	/**
	 * 获取某用户的详细信息
	 * @param userid
	 * @return
	 */
	public JEAPUser get(Integer userid);
	


	/**
	 * 根据用户名获取用户
	 * @param username
	 * @return
	 */
	public JEAPUser get(String username);
 
	
	/**
	 * 创建用户
	 * @param user 创建一个用户
	 * @return 此用户的用户id
	 */ 
	public Integer createUser(JEAPUser user);

	 
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void edit(JEAPUser user);
	
	
	/**
	 * 平台用户登录
	 * 登录成功后会在session中写入UserContext.CONTEXT_KEY为登录的EopUesr信息
	 * @param username
	 *            ,用户名称或email
	 * @param password
	 *            ,密码，此处应是未加密过的
	 * @return 验证状态，1为成功，0为登录失败
	 */
	public int login(String username, String password);

 
	
	
	
	
	/**
	 * 注销当前用户
	 */
	public void logout();
	
	
	
	public void  changeDefaultSite(Integer userid, Integer managerid, Integer defaultsiteid);
	
	
	
	/**
	 * 分页读取用户列表
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page list(String keyword,int pageNo,int pageSize);

	
	/**
	 * 删除一个用户，将会删除些用户的所有的站点
	 * @param userid
	 */
	public void delete(Integer userid);
	
	
	
	
	/**
	 * 检测当前用户是否登录了易族易站
	 * @return  1已经登录，0尚未登录或已超时
	 */
	public int checkIsLogin();
	
	
	/**
	 * 获取当前登录用户
	 * @return 当前登录的用户
	 * @throws RuntimeException 用户未登录抛出此异常
	 */
	public JEAPUser getCurrentUser();
	
	
}
