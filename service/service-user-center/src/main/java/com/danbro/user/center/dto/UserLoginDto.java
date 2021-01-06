package com.danbro.user.center.dto;

import javax.validation.constraints.NotEmpty;
import com.danbro.anotation.IsMobile;
import lombok.Data;

@Data
public class UserLoginDto {
    @IsMobile
    private String mobile;

    @NotEmpty(message = "密码不能为空!")
    private String password;
}
