package com.danbro.acl.controller;

import javax.annotation.Resource;
import com.danbro.acl.service.AclUserRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (AclUserRole)表控制层
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@RestController
@RequestMapping("admin/acl/userrole")
public class AclUserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private AclUserRoleService aclUserRoleService;


}