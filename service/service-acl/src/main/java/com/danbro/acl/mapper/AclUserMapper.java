package com.danbro.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.acl.entity.AclUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表(AclUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
public interface AclUserMapper extends BaseMapper<AclUser> {
}