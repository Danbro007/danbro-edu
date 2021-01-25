package com.danbro.anotation;

import com.danbro.impl.Insert;
import com.danbro.impl.Update;

import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

/**
 * @author Danrbo
 * @Classname IsTitle
 * @Description TODO
 * @Date 2021/1/22 16:50
 */
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
