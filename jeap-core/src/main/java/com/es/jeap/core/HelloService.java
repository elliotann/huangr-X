package com.es.jeap.core;

import org.springframework.stereotype.Service;



@Service("helloService")
public class HelloService implements IHelloService{

	public void sayHello() {
		System.out.println("here");
		
	}

}
