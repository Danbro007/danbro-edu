package com.danbro.user.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.user.center.controller.param.MemberParam;
import com.danbro.enity.UcenterMember;

/**
 * 会员表(UcenterMember)表服务接口
 *
 * @author makejava
 * @since 2021-01-05 20:29:56
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    /**
     * 通过会员的 phone 和 密码登录
     *
     * @param memberParam 会员登录参数
     * @return 返回 token
     */
    String login(MemberParam memberParam);

    /**
     * 通过会员手机号、密码、昵称和手机验证码来注册用户
     *
     * @param memberParam 注册会员的信息
     * @return 注册结果
     */
    UcenterMember register(MemberParam memberParam);

    /**
     * 微信用户的登录
     * 先通过微信用户的 openId 到数据库里看有没有此用户，有的话则返回 true，
     * 没有则添加给微信用户到数据库，添加成功返回 true，添加失败返回 false。
     *
     * @param ucenterMember 微信登录用户
     * @return token
     */
    String wechatUserLogin(UcenterMember ucenterMember);

    /**
     * 统计日期当天的用户注册数
     *
     * @param date 要统计的日期
     * @return 日期那天的用户注册数
     */
    Integer getRegisterStatisticByDate(String date);

    /**
     * 通过会员ID获取会员信息
     *
     * @param memberId 会员ID
     * @return 会员信息
     */
    UcenterMember getMemberInfoByMemberId(String memberId);

}