package com.danbro.vo;


import com.danbro.enity.UcenterMember;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname MemberVo
 * @Description TODO
 * @Date 2021/1/22 11:10
 * @Author Danrbo
 */
@Data
public class MemberVo implements Serializable, VoConvert<MemberVo, UcenterMember> {
    private static final long serialVersionUID = 476800313973564552L;

    @ApiModelProperty("会员ID")
    private String id;

    @ApiModelProperty("会员的微信ID")
    private String openid;

    @ApiModelProperty("会员的手机号")
    private String mobile;

    @ApiModelProperty("会员的昵称")
    private String nickname;

    @ApiModelProperty("会员性别")
    private Integer sex;

    @ApiModelProperty("会员的年龄")
    private Integer age;

    @ApiModelProperty("会员的头像")
    private String avatar;

    @ApiModelProperty("会员的签名")
    private String sign;

    @ApiModelProperty("会员是否被禁用")
    private Boolean isDisabled;

    @ApiModelProperty("创建会员的时间")
    private Date gmtCreate;

    @ApiModelProperty("修改会员的时间")
    private Date gmtModified;

    @Override
    public MemberVo convertFrom(UcenterMember ucenterMember) {
        BeanUtils.copyProperties(ucenterMember, this);
        return this;
    }
}
