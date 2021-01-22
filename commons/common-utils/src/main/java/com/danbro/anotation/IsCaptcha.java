package com.danbro.anotation;

import com.danbro.impl.Insert;
import org.hibernate.validator.constraints.Length;

/**
 * @Classname IsCaptcha
 * @Description TODO 校验验证码
 * @Date 2021/1/22 11:02
 * @Author Danrbo
 */
@Length(min = 4, max = 4, message = "验证码位数为 4 位，请重新输入！", groups = {Insert.class})
public @interface IsCaptcha {

}
