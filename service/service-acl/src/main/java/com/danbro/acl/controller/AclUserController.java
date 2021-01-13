package com.danbro.acl.controller;

import com.danbro.acl.service.AclUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户表(AclUser)表控制层
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@RestController
@RequestMapping("aclUser")
public class AclUserController {
    /**
     * 服务对象
     */
    @Resource
    private AclUserService aclUserService;


}