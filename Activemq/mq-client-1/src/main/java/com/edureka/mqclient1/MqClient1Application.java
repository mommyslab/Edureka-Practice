package com.edureka.mqclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;

@SpringBootApplication
public class MqClient1Application {
	
	@JmsListener(destination = "queue_1")
	public void listen(String message) {
		System.out.println("Message Received: " + message);
	}

	public static void main(String[] args) {
		SpringApplication.run(MqClient1Application.class, args);
	}

}
