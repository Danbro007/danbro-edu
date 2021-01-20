package com.danbro.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import com.danbro.impl.Insert;
import com.danbro.impl.Update;
import com.danbro.validator.PriceValidator;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PriceValidator.class})
public @interface IsPrice {
    boolean required() default true;

    String message() default "非法价格！";

    Class<?>[] groups() default {Insert.class, Update.class};

    Class<? extends Payload>[] payload() default {};
}
