package com.danbro.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.danbro.anotation.IsUUID;
import com.danbro.utils.ValidatorUtils;

/**
 * @Classname VideoValidator
 * @Description TODO UUID校验器
 * @Date 2021/1/21 19:38
 * @Created by Administrator
 */
public class UUIDValidator implements ConstraintValidator<IsUUID, String> {
    private boolean required = false;

    @Override
    public void initialize(IsUUID constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && required) {
            return ValidatorUtils.isUUID(value);
        }
        return false;
    }
}
