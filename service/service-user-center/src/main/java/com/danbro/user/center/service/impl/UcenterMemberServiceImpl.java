package com.danbro.user.center.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.user.center.dto.UserLoginDto;
import com.danbro.user.center.entity.UcenterMember;
import com.danbro.user.center.mapper.UcenterMemberMapper;
import com.danbro.user.center.service.UcenterMemberService;
import com.danbro.utils.JwtUtils;
import org.springframework.stereotype.Service;

/**
 * 会员表(UcenterMember)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 20:29:56
 */
@Service("ucenterMemberService")
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Override
    public String login(UserLoginDto user) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", user.getMobile());
        UcenterMember member = this.getOne(queryWrapper);
        if (member == null) {
            throw new MyCustomException(ResultCode.USER_NOT_EXIST);
        }
        if (!member.getPassword().equals(SecureUtil.md5(user.getPassword()))) {
            throw new MyCustomException(ResultCode.PASSWORD_NOT_CORRECT);
        }
        if (member.getIsDisabled()) {
            throw new MyCustomException(ResultCode.USER_IS_DISABLED);
        }
        return JwtUtils.getJwtToken(member.getId(), member.getNickname());
    }
}