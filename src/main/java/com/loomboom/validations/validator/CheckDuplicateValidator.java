package com.loomboom.validations.validator;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.loomboom.service.CommonService;
import com.loomboom.validations.annotations.CheckDuplicate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CheckDuplicateValidator implements ConstraintValidator<CheckDuplicate, Object> {

    private final CommonService commonService;
    private Class<?> tableName;
    private String fieldName;
    Logger logger = Logger.getLogger(getClass().getName());

    
    @Override
    public void initialize(CheckDuplicate constraintAnnotation) {
        this.tableName = constraintAnnotation.table();
        this.fieldName = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        
        try {
            return commonService.checkDuplicateValue(value, tableName, fieldName);
        } catch (Exception e) {
            
            System.out.println(e);
        }
        return false;
    }


}
