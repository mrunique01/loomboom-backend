package com.loomboom.service.impl;

import static com.loomboom.contants.ErrorMessage.*;
import static com.loomboom.contants.FileDirectoryConst.PROFILE_IMAGES;
import static com.loomboom.utils.StringUtils.empty;

import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.Product;
import com.loomboom.model.User;
import com.loomboom.repository.UserRepository;
import com.loomboom.service.FileUploadService;
import com.loomboom.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user, Long id, MultipartFile profilePhoto) {

        User oldUser = getUserById(id);

        if (getUserById(id) == null) {
            throw new ApiRequestException(USER_NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        String email = user.getEmail();
        User duplicateUser = findByEmail(email);
        if (duplicateUser != null && !(duplicateUser.getId().equals(id))) {
            throw new ApiRequestException(USER_EXISTS, HttpStatus.BAD_REQUEST);
        }
        if (user.getRoles() == null) {
            user.setRoles(oldUser.getRoles());
        }
        user.setId(id);
        user.setPassword(oldUser.getPassword());
        if (profilePhoto == null || profilePhoto.isEmpty()) {
            user.setProfilePhoto(oldUser.getProfilePhoto());
        } else {
            if (!profilePhoto.isEmpty()) {
                if (!profilePhoto.getContentType().equals("image/jpeg")) {
                    throw new ApiRequestException(ONLY_JPG_IMAGES, HttpStatus.BAD_REQUEST);
                }
                try {
                    String fileName = fileUploadService.uploadResourse(profilePhoto, PROFILE_IMAGES);
                    user.setProfilePhoto(fileName);
                } catch (IOException e) {
                    throw new ApiRequestException(ERROR_ON_UPLOAD, HttpStatus.BAD_REQUEST);

                }
            }
        }
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteUserById(Long id) {
        if (getUserById(id) == null) {
            throw new ApiRequestException(USER_NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public User getUserById(Long id) {
        if (!empty(id)) {
            return userRepository.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        if (!empty(email)) {
            return userRepository.findByEmail(email).orElse(null);
        }
        return null;
    }

}
