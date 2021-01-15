package com.danbro.acl.controller;

import javax.annotation.Resource;
import com.danbro.acl.service.AclRolePermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色权限(AclRolePermission)表控制层
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@RestController
@RequestMapping("admin/acl/role/permission")
public class AclRolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private AclRolePermissionService aclRolePermissionService;


}