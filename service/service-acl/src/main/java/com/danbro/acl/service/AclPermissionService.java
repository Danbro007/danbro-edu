package com.danbro.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.acl.dto.TreeNodePermissionDto;
import com.danbro.acl.entity.AclPermission;

import java.util.List;

/**
 * 权限(AclPermission)表服务接口
 *
 * @author makejava
 * @since 2021-01-13 14:18:14
 */
public interface AclPermissionService extends IService<AclPermission> {
    /**
     * 获取所有的权限（树形结构）
     *
     * @return 所有权限
     */
    List<TreeNodePermissionDto> getAllPermission();

    /**
     * 递归删除菜单
     *
     * @param permissionId 权限ID
     */
    void removePermissionRecursively(String permissionId);

}