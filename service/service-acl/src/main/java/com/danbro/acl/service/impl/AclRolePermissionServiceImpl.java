package com.danbro.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.acl.entity.AclRolePermission;
import com.danbro.acl.mapper.AclRolePermissionMapper;
import com.danbro.acl.service.AclRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限(AclRolePermission)表服务实现类
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@Service("aclRolePermissionService")
public class AclRolePermissionServiceImpl extends ServiceImpl<AclRolePermissionMapper, AclRolePermission> implements AclRolePermissionService {
}