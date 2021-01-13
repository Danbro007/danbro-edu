package com.danbro.acl.controller;

import com.danbro.acl.service.AclRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (AclRole)表控制层
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@RestController
@RequestMapping("aclRole")
public class AclRoleController {
    /**
     * 服务对象
     */
    @Resource
    private AclRoleService aclRoleService;

}