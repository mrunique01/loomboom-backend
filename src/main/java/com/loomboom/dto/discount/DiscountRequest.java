package com.loomboom.dto.discount;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static com.loomboom.contants.ErrorMessage.*;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class DiscountRequest {
    @NotBlank(message = REQUIRED_FIELD)
    @Length(max = 255)
    private String title;
    @NotBlank(message = REQUIRED_FIELD)
    @Length(max = 255)
    private String type;
    @NotNull(message = REQUIRED_FIELD)
    private Integer value;
    @NotNull(message = REQUIRED_FIELD)
    private Date expireAt;
    @NotNull(message = REQUIRED_FIELD)
    private Boolean active;

}
