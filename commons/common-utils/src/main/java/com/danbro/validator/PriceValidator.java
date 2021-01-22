package com.danbro.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.danbro.anotation.IsPrice;
import com.danbro.utils.ValidatorUtils;

/**
 * @Classname PriceValidator
 * @Description TODO
 * @Date 2021/1/20 21:11
 * @Created by Administrator
 */
public class PriceValidator implements ConstraintValidator<IsPrice, String> {
    private Boolean require = false;

    @Override
    public void initialize(IsPrice constraintAnnotation) {
        require = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && require) {
            return ValidatorUtils.isMoney(value);
        }
        return false;
    }
}
