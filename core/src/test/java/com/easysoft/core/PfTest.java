package com.easysoft.core;

import com.easysoft.core.manager.PersonManager;
import com.easysoft.core.model.Person;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class PfTest {
    private static PersonManager personManager;

    @BeforeClass
    public static void setUpBeforeClass(){
        try{
        /*ExtendXmlApplicationContext ctx = new ExtendXmlApplicationContext("com/btms/service/manager/test/mock-service-config.xml");
        ServiceClientMockFactory mockFactory = (ServiceClientMockFactory) ctx.getBean("mockFactory");
        personManager = mockFactory.getService(PersonManager.class);
       */
            ApplicationContext ctx = new ClassPathXmlApplicationContext("com/easysoft/core/manager/core-manager-config.xml");
            personManager = (PersonManager)ctx.getBean("personManager");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testcontext(){
        Person person = new Person();
        person.setName("elliotann");
        personManager.savePerson(person);
    }

    @Test
    public void testRetrieveById(){
        Person person = personManager.reteivePersonById(1);
        System.out.println(person.getName());

    }
}
