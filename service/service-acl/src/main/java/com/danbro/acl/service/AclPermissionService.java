package com.danbro.acl.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.acl.dto.TreeNodePermissionDto;
import com.danbro.acl.entity.AclPermission;

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
     * @param permissionId 删除的权限ID
     */
    void removePermissionRecursively(String permissionId);

    /**
     * 给角色分配权限
     *
     * @param roleId      角色ID
     * @param permissions 添加的权限
     */
    void insertRolePermission(String roleId, String[] permissions);

    /**
     * 通过用户 ID 查询用户所拥有的权限
     *
     * @param id 用户ID
     * @return 用户权限
     */
    List<String> getPermissionByUserId(String id);
}