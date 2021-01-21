package com.danbro.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.danbro.anotation.IsBool;
import com.danbro.utils.ValidatorUtils;

/**
 * @Classname BoolValidator
 * @Description TODO 布尔值校验器
 * @Date 2021/1/21 19:05
 * @Created by Administrator
 */
public class BoolValidator implements ConstraintValidator<IsBool, String> {
    private boolean required = false;

    @Override
    public void initialize(IsBool constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && required) {
            return ValidatorUtils.isBool(value);
        }
        return false;
    }
}
