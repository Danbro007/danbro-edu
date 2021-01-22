package com.danbro.anotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import com.danbro.validator.PositiveNumValidator;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PositiveNumValidator.class})
public @interface IsPositiveNum {
    boolean required() default true;

    String message() default "数字必须大于0！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
