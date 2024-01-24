package com.loomboom.dto.product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static com.loomboom.contants.ErrorMessage.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = REQUIRED_FIELD)
    @Length(max = 255)
    private String title;

    @NotBlank(message = REQUIRED_FIELD)
    @Length(max = 255)
    private String price;

    @NotBlank(message = REQUIRED_FIELD)
    private String description;

    @NotBlank(message = REQUIRED_FIELD)
    @Length(max = 255)
    private String category;

}
