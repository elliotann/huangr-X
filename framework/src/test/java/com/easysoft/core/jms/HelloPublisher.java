package com.easysoft.core.jms;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-23
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**

 * pub/sub方式的消息发送程序

 */

public class HelloPublisher {



    TopicConnection topicConnection;// JMS连接，属于Pub/Sub方式的连接

    TopicSession topicSession; //JMS会话，属于Pub/Sub方式的会话

    TopicPublisher topicPublisher; //消息发布者

    Topic topic; //主题



    public HelloPublisher(String factoryJNDI, String topicJNDI)

            throws JMSException, NamingException {

        Hashtable<String, String> env = new Hashtable<String, String>();

        //设置好连接JMS容器的属性，不同的容器需要的属性可能不同，需要查阅相关文档

        env.put(Context.INITIAL_CONTEXT_FACTORY,

                "org.jnp.interfaces.NamingContextFactory");

        env.put(Context.PROVIDER_URL, "localhost:1099");

        env.put("java.naming.rmi.security.manager", "yes");

        env.put(Context.URL_PKG_PREFIXES, "org.jboss.naming");



        //创建连接JMS容器的上下文(context)

        Context context = new InitialContext(env);



        //通过连接工厂的JNDI名查找ConnectionFactory

        TopicConnectionFactory topicFactory =

                (TopicConnectionFactory) context.lookup(factoryJNDI);



        //用连接工厂创建一个JMS连接

        topicConnection = topicFactory.createTopicConnection();



        //通过JMS连接创建一个Session

        topicSession = topicConnection.createTopicSession(false,

                Session.AUTO_ACKNOWLEDGE);



        //通过上下文查找到一个主题(topic)

        topic = (Topic) context.lookup(topicJNDI);



        //用session来创建一个特定主题的消息发送者

        topicPublisher = topicSession.createPublisher(topic);

    }





    /**

     * 发布一条文本消息

     * @param msg 待发布的消息

     * @throws JMSException

     */

    public void publish(String msg) throws JMSException {

        //用session来创建一个文本类型的消息

        TextMessage message = topicSession.createTextMessage();

        message.setText(msg);//设置消息内容

        topicPublisher.publish(topic, message);//消息发送，发送到特定主题

    }



    public void close() throws JMSException {

        topicSession.close();//关闭session

        topicConnection.close();//关闭连接

    }



    public static void main(String[] args)

            throws JMSException, NamingException {

        HelloPublisher publisher =

                new HelloPublisher("ConnectionFactory", "topic/testTopic");

        try {

            for (int i = 1; i < 11; i++) {

                String msg = "Hello World no. " + i;

                System.out.println("Publishing message: " + msg);

                publisher.publish(msg);

            }

            publisher.close();//session和connection用完之后一定记得关闭

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

}