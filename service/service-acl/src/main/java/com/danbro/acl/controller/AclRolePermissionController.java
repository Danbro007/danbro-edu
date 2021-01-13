package com.danbro.acl.controller;

import com.danbro.acl.service.AclRolePermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限(AclRolePermission)表控制层
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@RestController
@RequestMapping("aclRolePermission")
public class AclRolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private AclRolePermissionService aclRolePermissionService;


}