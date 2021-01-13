package com.danbro.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.acl.entity.AclPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限(AclPermission)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-13 14:18:14
 */
public interface AclPermissionMapper extends BaseMapper<AclPermission> {
}