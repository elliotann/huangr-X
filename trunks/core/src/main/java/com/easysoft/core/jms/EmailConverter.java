package com.easysoft.core.jms;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.HashMap;
import java.util.Map;

public class EmailConverter implements MessageConverter {
	public Message toMessage(Object obj, Session session) throws JMSException {
		if ((obj instanceof EmailModel)) {
			ActiveMQObjectMessage objMsg = (ActiveMQObjectMessage) session
					.createObjectMessage();
			Map map = new HashMap();
			map.put("EmailModel", (EmailModel) obj);
			objMsg.setObjectProperty("Map", map);
			return objMsg;
		}
		throw new JMSException("Object:[" + obj + "] is not Member");
	}

	public Object fromMessage(Message msg) throws JMSException {
		if ((msg instanceof ObjectMessage)) {
			return ((Map) ((ObjectMessage) msg).getObjectProperty("Map"))
					.get("EmailModel");
		}
		throw new JMSException("Msg:[" + msg + "] is not Map");
	}
}
