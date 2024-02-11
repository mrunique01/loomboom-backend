package com.loomboom.service;

import java.util.List;

import com.loomboom.model.User;

/**
 * UserService
 */
public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User findByEmail(String email);

    User updateUser(User user, Long id);

    Boolean deleteUserById(Long id);

}