package com.edureka.orderms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrdermsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdermsApplication.class, args);
	}

}
