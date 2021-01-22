package com.danbro.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.danbro.anotation.IsAssignID;
import com.danbro.utils.ValidatorUtils;

/**
 * @Classname IdValidator
 * @Description TODO Id 校验器
 * @Date 2021/1/20 19:56
 * @Created by Administrator
 */
public class IdValidator implements ConstraintValidator<IsAssignID, String> {
    private boolean required = false;

    @Override
    public void initialize(IsAssignID constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && required) {
            return ValidatorUtils.isAssignID(value);
        }
        return false;
    }
}
