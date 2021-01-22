package com.danbro.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import com.danbro.validator.BoolValidator;

/**
 * @Classname IsBool
 * @Description TODO
 * @Date 2021/1/21 19:10
 * @Created by Administrator
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {BoolValidator.class})
public @interface IsBool {
    boolean required() default true;

    String message() default "请正确输入布尔值！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
