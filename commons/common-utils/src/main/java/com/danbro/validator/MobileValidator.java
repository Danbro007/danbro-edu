package com.danbro.validator;

import com.danbro.anotation.IsMobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @Classname MobileValidator
 * @Description TODO 手机号检验器
 * @Date 2021/1/5 15:22
 * @Author Danrbo
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    /**
     * 校验规则
     * @param value 手机号
     * @param context 校验上下文
     * @return 校验结果
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // value就是要校验的数据了
        if (value != null && required) {
            // 手机号校验规则
            System.out.println(value);
            String regexp = "^(((\\+\\d{2}-)?0\\d{2,3}-\\d{7,8})|((\\+\\d{2}-)?(\\d{2,3}-)?([1][3,4,5,7,8][0-9]\\d{8})))$";
            boolean matches = Pattern.matches(regexp, value);
            System.out.println(matches);
            return matches;
        }
        return false;
    }
}
