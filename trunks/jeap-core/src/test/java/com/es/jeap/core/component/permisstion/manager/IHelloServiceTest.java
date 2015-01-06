package com.es.jeap.core.component.permisstion.manager;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.es.framework.db.pager.PageOption;
import com.es.jeap.core.component.permission.entity.AdminUser;
import com.es.jeap.core.component.permission.manager.IAdminUserManager;

public class IHelloServiceTest {
	@Test
	public void sayHello(){

		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		IAdminUserManager helloService = (IAdminUserManager)ctx.getBean("adminUserManager");
		AdminUser adminUser = new AdminUser();
		adminUser.setCreateBy("andy");
		PageOption pageOption = new PageOption();
		helloService.queryUsers(pageOption);
		System.out.println(pageOption.getTotalCount());
	}
}
