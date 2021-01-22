package com.danbro.user.center.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
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
            throw new MyCustomException(ResultCode.USER_NOT_EXIST);
        }
        if (!member.getPassword().equals(SecureUtil.md5(loginParam.getPassword()))) {
            throw new MyCustomException(ResultCode.PASSWORD_NOT_CORRECT);
        }
        if (member.getIsDisabled()) {
            throw new MyCustomException(ResultCode.USER_IS_DISABLED);
        }
        return JwtUtils.getJwtToken(member.getId(), member.getNickname(), member.getAvatar());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UcenterMember register(MemberParam memberParam) {
        // 校验在 redis 的验证码
        if (!memberParam.getCaptcha().equals(redisTemplate.opsForValue().get(memberParam.getMobile()))) {
            throw new MyCustomException(ResultCode.CAPTCHA_NOT_CORRECT);
        }
        // 验证码校验成功删除会员存储在 redis 的验证码
        redisTemplate.delete(memberParam.getMobile());
        // 查询用户信息
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", memberParam.getMobile());
        if (this.count(queryWrapper) > 0) {
            throw new MyCustomException(ResultCode.MOBILE_IS_EXIST);
        }
        // 添加会员
        UcenterMember member = memberParam.convertTo();
        this.save(member);
        return member;
    }

    @Override
    public String wechatUserLogin(UcenterMember ucenterMember) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", ucenterMember.getOpenid());
        UcenterMember member = this.getOne(queryWrapper);
        if (member == null) {
            this.save(ucenterMember);
            return JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname(), ucenterMember.getAvatar());
        }
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