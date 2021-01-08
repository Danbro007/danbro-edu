package com.danbro.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname UserInfoDto
 * @Description TODO 通过 jwt 把 token 解密出的用户信息
 * @Date 2021/1/8 10:53
 * @Author Danrbo
 */
@Data
@Accessors(chain = true)
@Builder
public class UserInfoDto {
    private String id;
    private String nickname;
    private String avatar;
}
