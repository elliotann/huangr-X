package com.easysoft.member.backend.manager;

import com.easysoft.member.backend.model.Depart;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : andy.huang
 * @since :
 */
public class DepartManagerTest {
    private ApplicationContext ctx = null;
    @Before
    public void before(){
        ctx = new ClassPathXmlApplicationContext("classpath*:spring/*.xml");
    }
    @Test
    public void saveDepart(){
        IDepartManager departManager = (IDepartManager)ctx.getBean("departManager");
        Depart depart = new Depart();
        depart.setName("中国");
        depart.setPid(0);
        depart.setCompId(1);
        departManager.saveDepart(depart);
    }
}
