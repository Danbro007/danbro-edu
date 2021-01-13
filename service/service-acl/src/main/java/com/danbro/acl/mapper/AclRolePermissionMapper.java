package com.danbro.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.acl.entity.AclRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限(AclRolePermission)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
public interface AclRolePermissionMapper extends BaseMapper<AclRolePermission> {
}