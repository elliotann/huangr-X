package com.easysoft.core.jms;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

public class EmailProducer {
	private JmsTemplate template;
	private Queue destination;

	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}

	public void setDestination(Queue destination) {
		this.destination = destination;
	}

	public void send(EmailModel emailModel) {
		this.template.convertAndSend(this.destination, emailModel);
	}
}
