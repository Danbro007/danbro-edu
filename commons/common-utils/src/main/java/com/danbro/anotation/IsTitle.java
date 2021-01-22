package com.danbro.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import com.danbro.impl.Insert;
import com.danbro.impl.Update;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NotBlank
@Max(value = 20)
public @interface IsTitle {
    boolean required() default true;

    String message() default "课程标题必须存在并且数字不能超过20个！";

    Class<?>[] groups() default {Insert.class, Update.class};

    Class<? extends Payload>[] payload() default {};
}
