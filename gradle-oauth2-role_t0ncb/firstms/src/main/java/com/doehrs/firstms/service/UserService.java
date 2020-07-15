package com.doehrs.firstms.service;

import com.doehrs.firstms.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getSingleUser(Long userId);
    Long createUser(User user);
    void deleteUser(Long userId);
    List<User> getAllUsersByName(String userName);

}
