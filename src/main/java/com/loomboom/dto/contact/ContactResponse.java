package com.loomboom.dto.contact;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String message;
    private String status;
}
