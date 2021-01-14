package com.danbro.acl.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.acl.entity.AclPermission;

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
    List<String> getUserPermissionValueByUserId(String userId);

    /**
     * 通过用户ID获取权限列表
     *
     * @return 权限列表
     */
    List<String> getUserAllPermissionValue();


    /**
     * 通过角色ID找到角色的权限
     *
     * @param roleId 角色ID
     * @return 角色权限
     */
    List<AclPermission> getRolePermissionByRoleId(String roleId);

    List<AclPermission> getUserPermissionByUserId(String id);

}