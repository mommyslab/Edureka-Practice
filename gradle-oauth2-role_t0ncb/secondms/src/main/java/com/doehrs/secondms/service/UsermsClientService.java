package com.doehrs.secondms.service;

import com.doehrs.secondms.model.User;

import java.util.List;
import java.util.Optional;

public interface UsermsClientService {
    List<User> getAllUsers();
    Optional<User> getSingleUser(Long userId);
    Long createUser(User user);
    void deleteUser(Long userId);
    List<User> getAllUsersByName(String userName);

}
