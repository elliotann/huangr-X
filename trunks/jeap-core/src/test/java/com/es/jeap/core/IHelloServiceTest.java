package com.es.jeap.core;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.es.jeap.core.manager.IHelloService;

public class IHelloServiceTest {
	@Test
	public void sayHello(){

		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		IHelloService helloService = (IHelloService)ctx.getBean("helloService");
		helloService.sayHello();
	}
}