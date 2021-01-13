package com.danbro.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.acl.dto.TreeNodePermissionDto;
import com.danbro.acl.entity.AclPermission;
import com.danbro.acl.mapper.AclPermissionMapper;
import com.danbro.acl.service.AclPermissionService;
import com.danbro.acl.utils.PermissionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限(AclPermission)表服务实现类
 *
 * @author makejava
 * @since 2021-01-13 14:18:14
 */
@Service("aclPermissionService")
public class AclPermissionServiceImpl extends ServiceImpl<AclPermissionMapper, AclPermission> implements AclPermissionService {
    @Override
    public List<TreeNodePermissionDto> getAllPermission() {
        List<AclPermission> list = this.list();
        return PermissionUtils.buildPermissionTree(list);
    }

    @Override
    public void removePermissionRecursively(String permissionId) {
        QueryWrapper<AclPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", permissionId).or().eq("pid", permissionId);
        this.remove(queryWrapper);
    }
}