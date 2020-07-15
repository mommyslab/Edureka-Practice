package com.doehrs.secondms.service;

import com.doehrs.secondms.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

@Service
public class UsermsClientServiceImpl implements UsermsClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsermsClientServiceImpl.class);

    private final OAuth2RestTemplate oAuth2RestTemplate;

    public UsermsClientServiceImpl(OAuth2RestTemplate oAuth2RestTemplate) {
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("getting all users");
        System.out.println("UsermsClientServiceImpl: Getting all users");
        List usersList = oAuth2RestTemplate.getForObject("http://localhost:9091/api/users", List.class);
        return usersList;
    }

    @Override
    public Optional<User> getSingleUser(Long userId) {
        LOGGER.info("Getting single user: {}", userId);
        User user = oAuth2RestTemplate.getForObject("http://localhost:9091/api/users/" + userId, User.class);
        return Optional.ofNullable(user);
    }

    @Override
    public Long createUser(User user) {
        final MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
        param.add("userName", user.getUserName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        param.add("Content-Type",MediaType.APPLICATION_JSON_VALUE );
        HttpEntity<User> request = new HttpEntity<>(user, httpHeaders);
        oAuth2RestTemplate.postForObject("http://localhost:9091/api/users", request, ResponseEntity.class);
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        oAuth2RestTemplate.delete("http://localhost:9091/api/users/" + userId, ResponseEntity.class);
    }

    @Override
    public List<User> getAllUsersByName(String userName) {
        return null;
    }
}
