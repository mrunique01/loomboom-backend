package com.loomboom.mapper;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.useraddress.UserAddressRequest;
import com.loomboom.dto.useraddress.UserAddressResponse;
import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.UserAddress;
import com.loomboom.model.User;

import static com.loomboom.contants.ErrorMessage.USER_NOT_EXISTS;
import static com.loomboom.contants.SuccessMessage.*;
import com.loomboom.service.UserAddressService;
import com.loomboom.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserAddressMapper {

    private final UserAddressService userAddressService;
    private final CommonMapper commonMapper;
    private final UserService userService;

    public UserAddressResponse createUserAddress(UserAddressRequest userAddressRequest, Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ApiRequestException(USER_NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        UserAddress userAddress = commonMapper.mapObject(userAddressRequest, UserAddress.class);
        userAddress.setUsers(user);
        return commonMapper.mapObject(userAddressService.createUserAddress(userAddress),
                UserAddressResponse.class);
    }

    public List<UserAddressResponse> getAllUserAddress(Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ApiRequestException(USER_NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        return commonMapper.mapListObject(userAddressService.findByUsers(user),
                UserAddressResponse.class);
    }

    public UserAddressResponse findUserAddressById(Long id) {
        return commonMapper.mapObject(userAddressService.findById(id), UserAddressResponse.class);
    }

    public UserAddressResponse updateUserAddress(Long id, Long userId, UserAddressRequest userAddressRequest) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ApiRequestException(USER_NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        UserAddress userAddress = commonMapper.mapObject(userAddressRequest, UserAddress.class);
        userAddress.setUsers(user);
        return commonMapper.mapObject(userAddressService.updateUserAddress(userAddress, id),
                UserAddressResponse.class);
    }

    public ApiResponse deleteUserAddress(Long id) {
        userAddressService.deleteUserAddressById(id);
        return new ApiResponse(true, ADDRESS_DELETED);
    }
}
