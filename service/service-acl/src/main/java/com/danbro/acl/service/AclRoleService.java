package com.danbro.acl.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.acl.controller.param.QueryRoleParam;
import com.danbro.acl.controller.vo.RoleVo;
import com.danbro.acl.controller.vo.TreeNodePermissionVo;
import com.danbro.acl.entity.AclRole;
import com.danbro.enity.OutPutPagingDto;

/**
 * (AclRole)表服务接口
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
public interface AclRoleService extends IService<AclRole> {
    /**
     * 通过角色ID获取角色信息
     *
     * @param roleId 角色ID
     * @return 角色信息
     */
    AclRole getRoleInfoById(String roleId);

    /**
     * 1、删除角色信息
     * 2、删除角色与权限的关系
     * 3、删除角色与用户的关系
     *
     * @param roleId 角色ID
     */
    void deleteRoleById(String roleId);

    /**
     * 添加或者更新角色信息
     *
     * @param aclRole 要添加或者更新的角色信息
     * @return 添加或者更新后的角色的信息
     */
    AclRole insertOrUpdate(AclRole aclRole);

    /**
     * 带有条件的分页查询角色
     *
     * @param page      当前页数
     * @param limit     每页显示的角色数
     * @param roleParam 查询条件
     * @return 查询结果
     */
    OutPutPagingDto<RoleVo> pagingGetRoleListByCondition(Integer page, Integer limit, QueryRoleParam roleParam);

    /**
     * 批量删除角色
     *
     * @param roleList 批量删除的角色ID列表
     */
    void batchDeleteRole(List<String> roleList);

    /**
     * 获取用户的角色列表
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<AclRole> getRoleListByUserId(String userId);

    /**
     * 获取角色权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    List<TreeNodePermissionVo> getRolePermission(String roleId);

}