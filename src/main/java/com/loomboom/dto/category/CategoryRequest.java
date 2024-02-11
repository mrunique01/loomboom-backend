package com.loomboom.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static com.loomboom.contants.ErrorMessage.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CategoryRequest {

    @NotBlank(message = REQUIRED_FIELD)
    @Length(max = 255)
    private String title;

    @NotNull(message = REQUIRED_FIELD)
    private Integer active;

}
