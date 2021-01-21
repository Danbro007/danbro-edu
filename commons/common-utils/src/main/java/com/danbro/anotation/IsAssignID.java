package com.danbro.anotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import com.danbro.validator.IdValidator;

/**
 * @Classname IsId
 * @Description TODO ID校验器注解
 * @Date 2021/1/5 15:23
 * @Author Danrbo
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IdValidator.class})
public @interface IsAssignID {
    boolean required() default true;

    String message() default "非法ID！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
