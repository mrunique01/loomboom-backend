package com.loomboom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.product.ProductRequest;
import com.loomboom.dto.user.UserRequest;
import com.loomboom.dto.user.UserResponse;
import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.mapper.UserMapper;
import com.loomboom.service.FileUploadService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import static com.loomboom.contants.ErrorMessage.FILE_NOT_FOUND;
import static com.loomboom.contants.ErrorMessage.SOMETHING_WENT_WRONG;
import static com.loomboom.contants.FileDirectoryConst.PROFILE_IMAGES;
import static com.loomboom.contants.PathConstants.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final FileUploadService fileUploadService;


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

    @PutMapping(UPDATE_USER)
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId,
            @RequestPart("user") @Valid UserRequest userRequest,
            @RequestPart(name = "profilePhoto", required = false) MultipartFile profilePhoto) {

        UserResponse user = userMapper.updateUser(userId, userRequest, profilePhoto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(DELETE_USER)
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        ApiResponse response = userMapper.deleteUser(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = GET_USER_IMAGE, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getUserImage(@PathVariable String imageName,
            HttpServletResponse response) {
        InputStream resource;
        try {
            resource = fileUploadService.getResourse(PROFILE_IMAGES, imageName);

            response.setContentType(MediaType.IMAGE_JPEG_VALUE);

            StreamUtils.copy(resource, response.getOutputStream());

        } catch (FileNotFoundException e) {
            throw new ApiRequestException(FILE_NOT_FOUND, HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            throw new ApiRequestException(SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
        }

    }

}
