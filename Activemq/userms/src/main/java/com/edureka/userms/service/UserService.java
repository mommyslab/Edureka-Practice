package com.edureka.userms.service;

import java.util.List;
import java.util.Optional;

import com.edureka.userms.model.User;

public interface UserService {
	List<User> getAllUsers();
	Optional<User> getSingleUser(Long userId);
	void createUser(User user);
	void updateUser(User user);
	void partiallyUpdateUser(User user);
	void deleteUser(Long userId);
	Object getAllOrders();
	

}
