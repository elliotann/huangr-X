package com.easysoft.jeap.service;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huangxa on 2014/7/4.
 */
public class TestUserTableService {
    ApplicationContext ctx = null;
    @Before
    public void before(){
        ctx = new ClassPathXmlApplicationContext(new String[]{"classpath*:spring/*.xml"});
    }
    @Test
    public void getUser(){
        IUserTableService userTableService = (IUserTableService)ctx.getBean("userTableService");
        Assert.assertEquals(2,userTableService.getUser().size());
    }
}
