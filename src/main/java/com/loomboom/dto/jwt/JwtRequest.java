package com.loomboom.dto.jwt;

import static com.loomboom.contants.ErrorMessage.EMAIL_CANNOT_BE_EMPTY;
import static com.loomboom.contants.ErrorMessage.INCORRECT_EMAIL;
import static com.loomboom.contants.ErrorMessage.PASSWORD_CHARACTER_LENGTH;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtRequest {

    @NotBlank(message = EMAIL_CANNOT_BE_EMPTY)
    @Email(message = INCORRECT_EMAIL)
    @Length(max = 255)
    private String email;

    @Size(min = 6, max = 16, message = PASSWORD_CHARACTER_LENGTH)
    private String password;

}
