package com.danbro.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.danbro.anotation.IsPositiveNum;
import com.danbro.utils.ValidatorUtils;

/**
 * @Classname PageValidator
 * @Description TODO 正数校验器
 * @Date 2021/1/20 20:11
 * @Created by Administrator
 */
public class PositiveNumValidator implements ConstraintValidator<IsPositiveNum, String> {
    private Boolean require = false;

    @Override
    public void initialize(IsPositiveNum constraintAnnotation) {
        require = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && require) {
            return ValidatorUtils.isPositiveNumber(value);
        }
        return false;
    }
}
