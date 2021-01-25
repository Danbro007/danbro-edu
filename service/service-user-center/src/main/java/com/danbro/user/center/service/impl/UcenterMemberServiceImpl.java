package com.danbro.user.center.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import com.danbro.user.center.controller.param.MemberParam;
import com.danbro.enity.UcenterMember;
import com.danbro.user.center.mapper.UcenterMemberMapper;
import com.danbro.user.center.service.UcenterMemberService;
import com.danbro.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员表(UcenterMember)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 20:29:56
 */
@Service("ucenterMemberService")
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(MemberParam loginParam) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", loginParam.getMobile());
        UcenterMember member = this.getOne(queryWrapper);
        if (member == null) {
            throw new EduException(ResultCode.USER_NOT_EXIST);
        }
        if (!member.getPassword().equals(SecureUtil.md5(loginParam.getPassword()))) {
            throw new EduException(ResultCode.USER_PASSWORD_NOT_CORRECT);
        }
        if (member.getIsDisabled()) {
            throw new EduException(ResultCode.USER_IS_DISABLED);
        }
        return JwtUtils.getJwtToken(member.getId(), member.getNickname(), member.getAvatar());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UcenterMember register(MemberParam memberParam) {
        // 校验在 redis 的验证码
        if (!memberParam.getCaptcha().equals(redisTemplate.opsForValue().get(memberParam.getMobile()))) {
            throw new EduException(ResultCode.MEMBER_REGISTER_CAPTCHA_ERROR);
        }
        // 验证码校验成功删除会员存储在 redis 的验证码
        redisTemplate.delete(memberParam.getMobile());
        // 查询用户信息
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", memberParam.getMobile());
        if (this.count(queryWrapper) > 0) {
            throw new EduException(ResultCode.MEMBER_MOBILE_IS_EXIST);
        }
        // 添加会员
        UcenterMember member = memberParam.convertTo();
        boolean success = this.save(member);
        if (!success) {
            throw new EduException(ResultCode.MEMBER_REGISTER_FAILURE);
        }
        return member;
    }

    @Override
    public String wechatUserLogin(UcenterMember ucenterMember) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", ucenterMember.getOpenid());
        UcenterMember member = this.getOne(queryWrapper);
        // 用户没有注册过
        if (member == null) {
            boolean success = this.save(ucenterMember);
            if (!success) {
                throw new EduException(ResultCode.MEMBER_REGISTER_FAILURE);
            }
            return JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname(), ucenterMember.getAvatar());
        }
        // 用户之前注册过
        return JwtUtils.getJwtToken(member.getId(), member.getNickname(), member.getAvatar());

    }

    @Override
    public Integer getRegisterStatisticByDate(String date) {
        return this.baseMapper.getRegisterUserNumByDate(date);
    }

    @Override
    public UcenterMember getMemberInfoByMemberId(String memberId) {
        return this.getById(memberId);
    }
}