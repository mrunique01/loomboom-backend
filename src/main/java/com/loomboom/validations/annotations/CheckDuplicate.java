package com.loomboom.validations.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.loomboom.validations.validator.CheckDuplicateValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER ,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CheckDuplicateValidator.class)
public @interface CheckDuplicate {

    Class<?> table();

    String field() default "";

    // error message
    public String message() default "Duplicate Value";

    // represents group of constraints
    public Class<?>[] groups() default {};

    // represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};

}
