package com.edureka.userms.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.edureka.userms.model.User;
import com.edureka.userms.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;
	private final RestTemplate restTemplate;
	
	@Value("${orderms.url}")
	private String ordermsUrl;
	
	public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		LOGGER.info("Get All users in service");
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getSingleUser(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partiallyUpdateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@HystrixCommand(fallbackMethod = "getResponseFromFallBack")
	public Object getAllOrders() {
		LOGGER.info("*************************************");
		LOGGER.info("About to call OrderMS");
		return restTemplate.getForObject(ordermsUrl, Object.class);
	}
	
	private Object getResponseFromFallBack() {
		// return from cache
		// not in cache: return either empty or error reponse
		LOGGER.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.info("Fallback mechanism invoked");
		User user = new User();
		user.setId(1L);
		user.setName("A");
		
		User user2 = new User();
		user2.setId(2L);
		user2.setName("B");
		
		return Arrays.asList(user, user2);
	}

	

}
