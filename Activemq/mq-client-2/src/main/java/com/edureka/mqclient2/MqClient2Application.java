package com.edureka.mqclient2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;

@SpringBootApplication
public class MqClient2Application {
	
	@JmsListener(destination = "queue_2")
	public void listen(String message) {
		System.out.println("Message Received: " + message);
	}

	public static void main(String[] args) {
		SpringApplication.run(MqClient2Application.class, args);
	}

}
