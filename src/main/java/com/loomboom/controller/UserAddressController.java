package com.loomboom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import static com.loomboom.contants.PathConstants.*;

import java.util.List;

import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.useraddress.UserAddressRequest;
import com.loomboom.dto.useraddress.UserAddressResponse;
import com.loomboom.mapper.UserAddressMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressMapper userAddressMapper;

    @GetMapping(ALL_USER_ADDRESS)
    public ResponseEntity<List<UserAddressResponse>> getUserAddress(@PathVariable Long userId) {
        List<UserAddressResponse> userAddresss = userAddressMapper.getAllUserAddress(userId);
        return ResponseEntity.ok(userAddresss);
    }

    @GetMapping(USER_ADDRESS_BY_ID)
    public ResponseEntity<UserAddressResponse> getUserAddressById(
            @PathVariable Long userAddressId) {
        UserAddressResponse userAddress = userAddressMapper.findUserAddressById(userAddressId);
        return ResponseEntity.ok(userAddress);
    }

    @PostMapping(CREATE_USER_ADDRESS)
    public ResponseEntity<UserAddressResponse> createUserAddress(
            @RequestBody @Valid UserAddressRequest userAddressRequest,@PathVariable Long userId) {
        UserAddressResponse userAddressResponse = userAddressMapper.createUserAddress(userAddressRequest,userId);
        return ResponseEntity.ok(userAddressResponse);
    }

    @PutMapping(UPDATE_USER_ADDRESS)
    public ResponseEntity<UserAddressResponse> updateUserAddress(@PathVariable Long userAddressId,
            @PathVariable Long userId,
            @RequestBody @Valid UserAddressRequest userAddressRequest) {

        UserAddressResponse userAddress = userAddressMapper.updateUserAddress(userAddressId, userId,
                userAddressRequest);
        return ResponseEntity.ok(userAddress);
    }

    @DeleteMapping(DELETE_USER_ADDRESS)
    public ResponseEntity<ApiResponse> deleteUserAddress(@PathVariable Long userAddressId) {
        ApiResponse response = userAddressMapper.deleteUserAddress(userAddressId);
        return ResponseEntity.ok(response);
    }

}
