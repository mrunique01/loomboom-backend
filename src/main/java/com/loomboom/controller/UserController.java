package com.loomboom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.user.RegisterRequest;
import com.loomboom.dto.user.UserRequest;
import com.loomboom.dto.user.UserResponse;
import com.loomboom.mapper.UserMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import static com.loomboom.contants.PathConstants.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    @GetMapping(ALL_USERS)
    public ResponseEntity<List<UserResponse>> getUsers() {

        List<UserResponse> users = userMapper.getAllUsers();
        return ResponseEntity.ok(users);

    }

    @GetMapping(USER_BY_ID)
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        UserResponse user = userMapper.findUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping(REGISTRATION)
    public ResponseEntity<UserResponse> registerUser(
            @RequestBody @Valid RegisterRequest registerRequest) {
        UserResponse userResponse = userMapper.registerUser(registerRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping(UPDATE_USER)
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId,
            @RequestBody @Valid UserRequest userRequest) {

        UserResponse user = userMapper.updateUser(userId, userRequest);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(DELETE_USER)
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        ApiResponse response = userMapper.deleteUser(userId);
        return ResponseEntity.ok(response);
    }

}
