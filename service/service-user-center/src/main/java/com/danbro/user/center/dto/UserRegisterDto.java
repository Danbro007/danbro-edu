package com.danbro.user.center.dto;

import javax.validation.constraints.NotEmpty;
import com.danbro.anotation.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRegisterDto {
    @IsMobile
    private String mobile;
    @NotEmpty(message = "密码不能为空!")
    private String password;
    @NotEmpty(message = "昵称不能为空！")
    private String nickname;
    @NotEmpty(message = "验证码不能为空！")
    @Length(min = 4, max = 4, message = "验证码位数为 4 位，请重新输入！")
    private String captcha;
}
