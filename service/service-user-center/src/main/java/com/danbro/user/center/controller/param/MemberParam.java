package com.danbro.user.center.controller.param;

import javax.validation.constraints.NotEmpty;

import cn.hutool.crypto.SecureUtil;
import com.danbro.anotation.IsCaptcha;
import com.danbro.anotation.IsMobile;
import com.danbro.anotation.IsPassword;
import com.danbro.impl.Insert;
import com.danbro.impl.ParamConvert;
import com.danbro.impl.Select;
import com.danbro.enity.UcenterMember;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author liweimo
 */
@Data
@ApiModel("会员登录")
public class MemberParam implements Serializable, ParamConvert<UcenterMember> {
    private static final long serialVersionUID = 4220883067994380589L;
    @IsMobile
    private String mobile;

    @IsPassword
    private String password;

    @NotEmpty(message = "昵称不能为空！", groups = {Select.class, Insert.class})
    private String nickname;

    @IsCaptcha
    private String captcha;

    @Override
    public UcenterMember convertTo() {
        UcenterMember member = new UcenterMember();
        BeanUtils.copyProperties(this, member);
        member.setPassword(SecureUtil.md5(member.getPassword()));
        return member;
    }
}
