package com.loomboom.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.loomboom.model.User;

/**
 * UserService
 */
public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User findByEmail(String email);

    User updateUser(User user, Long id, MultipartFile profilePhoto);

    Boolean deleteUserById(Long id);

}