package com.loomboom.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.loomboom.dto.api.ApiResponse;
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

    public List<UserResponse> getAllUsers() {
        return commonMapper.mapListObject(userService.getAllUsers(), UserResponse.class);
    }

    public UserResponse findUserById(Long id) {
        return commonMapper.mapObject(userService.getUserById(id), UserResponse.class);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest, MultipartFile profilePhoto) {
        User user = commonMapper.mapObject(userRequest, User.class);
        return commonMapper.mapObject(userService.updateUser(user, id,profilePhoto), UserResponse.class);
    }

    public ApiResponse deleteUser(Long id) {
        userService.deleteUserById(id);
        return new ApiResponse(true, USER_DELETED);
    }

}
