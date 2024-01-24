package com.loomboom.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.user.RegisterRequest;
import com.loomboom.dto.user.UserRequest;
import com.loomboom.dto.user.UserResponse;
import com.loomboom.model.User;

import static com.loomboom.contants.SuccessMessage.*;
import com.loomboom.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final UserService userService;
    private final CommonMapper commonMapper;

    public UserResponse registerUser(RegisterRequest registerRequest) {

        User user = commonMapper.mapObject(registerRequest, User.class);
        return commonMapper.mapObject(userService.registerUser(user), UserResponse.class);
    }

    public List<UserResponse> getAllUsers() {
        return commonMapper.mapListObject(userService.getAllUsers(), UserResponse.class);
    }

    public UserResponse findUserById(Long id) {
        return commonMapper.mapObject(userService.getUserById(id), UserResponse.class);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = commonMapper.mapObject(userRequest, User.class);
        return commonMapper.mapObject(userService.updateUser(user, id), UserResponse.class);
    }

    public ApiResponse deleteUser(Long id) {
        userService.deleteUserById(id);
        return new ApiResponse(true, USER_DELETED);
    }
}
