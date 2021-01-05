package com.danbro.user.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.user.center.dto.UserLoginDto;
import com.danbro.user.center.dto.UserRegisterDto;
import com.danbro.user.center.entity.UcenterMember;
import java.util.List;

/**
 * 会员表(UcenterMember)表服务接口
 *
 * @author makejava
 * @since 2021-01-05 20:29:56
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    /**
     * 通过用户的 phone 和 密码登录
     * @param user 用户信息
     * @return 返回 token
     */
    String login(UserLoginDto user);

    /**
     * 通过用户手机号、密码、昵称和手机验证码来注册用户
     * @param user 注册用户的信息
     * @return 注册结果
     */
    Boolean register(UserRegisterDto user);
}