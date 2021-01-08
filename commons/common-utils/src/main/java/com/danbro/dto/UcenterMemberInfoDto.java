package com.danbro.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname UcenterMemberInfoDto
 * @Description TODO
 * @Date 2021/1/8 16:11
 * @Author Danrbo
 */
@Data
public class UcenterMemberInfoDto implements Serializable {
    /**
     * 会员id
     */
    private String id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;

}
