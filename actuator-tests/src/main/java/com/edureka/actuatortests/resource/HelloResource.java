package com.edureka.actuatortests.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

	@GetMapping("/hello")
	public String getHello() {
		System.out.println("Hello Resource is called");
		return "Hello World";
	}
	
	@GetMapping("/hello/{user}")
	public String getHello(@PathVariable String user) {
		System.out.println("Hello-User Resource is called");
		return "Hello :" + user;
	}
}
