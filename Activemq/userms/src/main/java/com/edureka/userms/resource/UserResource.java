package com.edureka.userms.resource;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.edureka.userms.model.User;
import com.edureka.userms.service.UserService;
import com.edureka.userms.service.UserServiceImpl;

@RestController
public class UserResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
	private final UserService userService;

	public UserResource(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public ResponseEntity getAllUsers() {
		LOGGER.info("Get All users in Resource");
		List<User> allUsers = userService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity getSingleUser(@PathVariable Long userId) {
		Optional<User> singleUser = userService.getSingleUser(userId);
		if (singleUser.isPresent()) {
			return ResponseEntity.ok(singleUser.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/allOrders")
	public Object getAllOrders() {
		return userService.getAllOrders();
	}
	
	

}
