package com.danbro.anotation;

import com.danbro.impl.Insert;
import com.danbro.impl.Select;
import com.danbro.impl.Update;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @Classname IsPassword
 * @Description TODO
 * @Date 2021/1/22 10:56
 * @Author Danrbo
 */
@NotEmpty(message = "密码不能为空!")
@Length(min = 8, max = 30, message = "密码长度最少为 8 位！")
public @interface IsPassword {
    Class<?>[] groups() default {Insert.class, Update.class, Select.class};

}
