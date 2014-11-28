package com.easysoft.member.backend.manager;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationCtxTest {
	@Test
	public void testApplicationCtx(){
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:spring/*.xml");
		System.out.print("load bean count is "+ctx.getBeanDefinitionCount());
	}
}
