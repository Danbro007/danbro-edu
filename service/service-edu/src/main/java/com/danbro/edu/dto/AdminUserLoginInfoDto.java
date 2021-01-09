package com.danbro.edu.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname UserLoginInfoDto
 * @Description TODO 后台用户登录信息
 * @Date 2021/1/9 15:00
 * @Created by Administrator
 */
@Data
@Accessors(chain = true)
public class AdminUserLoginInfoDto {
    private String roles;
    private String name;
    private String avatar;
}
