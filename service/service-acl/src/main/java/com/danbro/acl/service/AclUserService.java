package com.danbro.acl.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.acl.dto.AclRoleDto;
import com.danbro.acl.dto.AclUserDto;
import com.danbro.acl.entity.AclUser;
import com.danbro.enity.OutPutPagingDto;

/**
 * 用户表(AclUser)表服务接口
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
public interface AclUserService extends IService<AclUser> {
    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    AclUserDto getUserInfoById(String userId);

    /**
     * 删除单个用户
     *
     * @param userId 用户ID
     */
    void deleteUserById(String userId);

    /**
     * 添加或者更新用户信息
     *
     * @param aclUserDto 添加或者更新的用户信息
     * @return 添加或者更新完毕的用户信息
     */
    AclUserDto insertOrUpdate(AclUserDto aclUserDto);

    /**
     * 批量删除用户信息
     *
     * @param userList 删除的用户ID列表
     */
    void batchDeleteUser(List<String> userList);

    /**
     * 带有条件的分页查询用户
     *
     * @param page           当前页数
     * @param limit          每页显示的用户数
     * @param queryCondition 查询条件
     * @return 查询结果
     */
    OutPutPagingDto<AclUserDto> pagingGetUserListByCondition(Integer page, Integer limit, AclUserDto queryCondition);

    /**
     * 获取用户的所有角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<AclRoleDto> getUserRoleListByUserId(String userId);

    /**
     * 给用户添加角色
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     */
    void inertUserRole(String userId, String roleId);

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    AclUser getUserInfoByUsername(String username);

}