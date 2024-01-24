package com.loomboom.service.impl;


import static com.loomboom.contants.ErrorMessage.*;
import static com.loomboom.utils.StringUtils.empty;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.User;
import com.loomboom.repository.UserRepository;
import com.loomboom.service.UserService;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User registerUser(User user) {
        if (findByEmail(user.getEmail()) != null) {
            throw new ApiRequestException(USER_EXISTS, HttpStatus.BAD_REQUEST);
        }
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(User user, Long id) {
        if (empty(id) || (!empty(id) && (getUserById(id) == null))) {
            throw new ApiRequestException(USER_NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        String email = user.getEmail();
        if (!empty(email) && findByEmail(email) != null) {
            throw new ApiRequestException(DUPLICATE_VALUE, HttpStatus.BAD_REQUEST);
        }
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteUserById(Long id) {
        if (empty(id) || (!empty(id) && getUserById(id) == null)) {
            throw new ApiRequestException(USER_NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        userRepository.deleteById(id);
        return true;
    }

}
