package com.edureka.actuatortests.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
public class HelloResourceV2 extends HelloResource {

	@GetMapping("/hello/{user}") // /v2/hello/{user}
	public String getHello(@PathVariable String user) {
		System.out.println("Hello-User-V2 Resource is called");
		return "Hello V2: " + user;
	}
}
