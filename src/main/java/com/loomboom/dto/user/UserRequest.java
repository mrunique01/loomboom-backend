package com.loomboom.dto.user;

import static com.loomboom.contants.ErrorMessage.EMAIL_CANNOT_BE_EMPTY;
import static com.loomboom.contants.ErrorMessage.EMPTY_FIRST_NAME;
import static com.loomboom.contants.ErrorMessage.EMPTY_GENDER;
import static com.loomboom.contants.ErrorMessage.EMPTY_LAST_NAME;
import static com.loomboom.contants.ErrorMessage.EMPTY_PHONE_NUMBER;
import static com.loomboom.contants.ErrorMessage.INCORRECT_EMAIL;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotBlank(message = EMPTY_FIRST_NAME)
    @Length(max = 255)
    private String firstName;

    @NotBlank(message = EMPTY_LAST_NAME)
    @Length(max = 255)
    private String lastName;

    @NotBlank(message = EMPTY_GENDER)
    @Length(max = 255)
    private String gender;

    @NotBlank(message = EMAIL_CANNOT_BE_EMPTY)
    @Email(message = INCORRECT_EMAIL)
    @Length(max = 255)
    private String email;

    private String password;

    @NotBlank(message = EMPTY_PHONE_NUMBER)
    @Length(max = 255)
    private String phone;
}
