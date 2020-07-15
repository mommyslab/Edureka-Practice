package com.edureka.userms.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edureka.userms.model.User;
import com.edureka.userms.service.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
//@RequestMapping(value = "/api/users")
public class UserResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
	private final UserService userService;
	
	// TDD recommends to use constructor injection
	public UserResource(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		LOGGER.info("**** UserResource - getting all users ****");
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable Long userId) {
		LOGGER.info("**** UserResource - getting single user ****");
		Optional<User> singleUser = userService.getSingleUser(userId);
		if (singleUser.isPresent()) {
			return ResponseEntity.ok(singleUser.get());
		}
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody(required = true) User user, UriComponentsBuilder ucBuilder) {
		if (user.getId() != null) {
//			throw new RuntimeException("User already has an Id");
			return ResponseEntity.badRequest().build();
		}
		Optional<User> existingUser = userService.findByPhoneNumber(user.getPhoneNumber());
		if (existingUser.isPresent()) {
//			throw new RuntimeException("User with provided phone number exists");
			return ResponseEntity.badRequest().build();
		}

		User newUser = userService.createUser(user);
		if (newUser == null) {
			return ResponseEntity.noContent().build();
		}
		// Two ways of generating location
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
//				"/{id}").buildAndExpand(newUser.getId()).toUri();
		URI location = ucBuilder.path("/{id}").buildAndExpand(newUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.accepted().build();
	}
	
	@GetMapping("/allOrders")
	public Object getAllOrders() {
		return userService.getAllOrders();
	}

}
