package com.loomboom.dto.user;

import java.util.List;

import com.loomboom.model.UserAddress;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String phone;
    private String profilePhoto;
}
