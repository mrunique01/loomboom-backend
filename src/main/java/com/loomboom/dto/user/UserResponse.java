package com.loomboom.dto.user;

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
    private String password;
    private String phone;
}
