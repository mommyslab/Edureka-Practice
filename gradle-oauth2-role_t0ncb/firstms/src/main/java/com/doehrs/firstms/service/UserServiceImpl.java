package com.doehrs.firstms.service;

import com.doehrs.firstms.model.User;
import com.doehrs.firstms.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("getting all users");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getSingleUser(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Long createUser(User user) {
        User newUser = userRepository.save(user);
        return newUser.getUserId();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsersByName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
