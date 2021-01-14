package com.danbro.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.acl.entity.AclPermission;

import java.util.List;

/**
 * 权限(AclPermission)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-13 14:18:14
 */
public interface AclPermissionMapper extends BaseMapper<AclPermission> {
    /**
     * 通过用户ID获取权限列表
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> getUserPermissionByUserId(String userId);

}