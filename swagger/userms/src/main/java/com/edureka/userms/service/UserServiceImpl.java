package com.edureka.userms.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.edureka.userms.model.User;
import com.edureka.userms.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;
//	private final RestTemplate restTemplate;
	private final OAuth2RestTemplate oAuth2RestTemplate;
	
	@Value("${orderms.url}")
	private String ordermsUrl;
	
	public UserServiceImpl(UserRepository userRepository, OAuth2RestTemplate oAuth2RestTemplate) {
//		this.restTemplate = restTemplate;
		this.userRepository = userRepository;
		this.oAuth2RestTemplate = oAuth2RestTemplate;
	}

	@Override
	public List<User> getAllUsers() {
		LOGGER.info("**** UserServiceImpl - getting all users ****");
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getSingleUser(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public Optional<User> findByPhoneNumber(Long phoneNumber) {
		return userRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public User createUser(User user) {
		User newUser = userRepository.save(user);
		return newUser;
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
//	@HystrixCommand(fallbackMethod = "getResponseFromFallback")
	public Object getAllOrders() {
		//Step# 1: POST -d grant_type=client_credentials http://candy:123@localhost:9000/oauth/token --> cache
		//Step# 2: restTemplate.getForObject(ordermsUrl, Object.class);
		// rather use OrderWrapper class instead of Object class
//		return restTemplate.getForObject(ordermsUrl, Object.class);
		return oAuth2RestTemplate.getForObject(ordermsUrl, Object.class);
	}
	
	private Object getResponseFromFallback() {
		// get it from cache
		User user1 = new User();
		user1.setId(1L);
		user1.setName("A");
		
		User user2 = new User();
		user2.setId(2L);
		user2.setName("B");
		
		return Arrays.asList(user1, user2);
	}


}
