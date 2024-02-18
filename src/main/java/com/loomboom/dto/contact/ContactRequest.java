package com.loomboom.dto.contact;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static com.loomboom.contants.ErrorMessage.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ContactRequest {

    @NotBlank(message = REQUIRED_FIELD)
    @Length(max = 255)
    private String firstName;
    @NotBlank(message = REQUIRED_FIELD)
    @Length(max = 255)
    private String lastName;
    @NotBlank(message = REQUIRED_FIELD)
    @Length(max = 255)
    private String gender;
    @NotBlank(message = REQUIRED_FIELD)
    @Length(max = 255)
    private String email;
    @NotBlank(message = REQUIRED_FIELD)
    @Length(max = 255)
    private String message;
    @NotBlank(message = REQUIRED_FIELD)
    @Length(max = 255)
    private String status;

}
