package com.danbro.user.center.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.user.center.entity.UcenterMember;

/**
 * 会员表(UcenterMember)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-05 20:29:56
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    /**
     * 通过日期查找到那天注册的用户数
     *
     * @param date 日期
     * @return 注册的用户数
     */
    Integer getRegisterUserNumByDate(String date);
}