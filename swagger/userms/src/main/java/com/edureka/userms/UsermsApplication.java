package com.edureka.userms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@RestController
@EnableHystrix
public class UsermsApplication {
	
	@Value("${my-property}")
	private String myProperty;
	@Value("${your-property}")
	private String yourProperty;
	
	@GetMapping("/properties")
	public List<String> getProperty() {
		List<String> properties = new ArrayList<String>();
		properties.add(myProperty);
		properties.add(yourProperty);
		
		return properties;
	}

	public static void main(String[] args) {
		SpringApplication.run(UsermsApplication.class, args);
	}

}
