package com.danbro.user.center.dto;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import java.util.List;

/**
 * @Classname WeChatUserInfo
 * @Description TODO 通过微信的 accessToken和 openid 返回的微信用户信息
 * @Date 2021/1/6 14:31
 * @Author Danrbo
 */
@Data
public class WeChatUserInfoDto {
    private String openid;
    private String nickname;
    private Integer sex;
    private String province;
    private String city;
    private String country;
    /**
     * 用户头像
     */
    @Alias("avatar")
    private String headImgUrl;
    private List<String> privilege;
    private String unionid;

}
