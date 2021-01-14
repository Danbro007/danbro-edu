package com.danbro.acl.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.acl.dto.TreeNodePermissionDto;
import com.danbro.acl.entity.AclPermission;
import com.danbro.acl.entity.AclRolePermission;
import com.danbro.acl.entity.AclUser;
import com.danbro.acl.mapper.AclPermissionMapper;
import com.danbro.acl.service.AclPermissionService;
import com.danbro.acl.service.AclRolePermissionService;
import com.danbro.acl.service.AclUserRoleService;
import com.danbro.acl.service.AclUserService;
import com.danbro.acl.utils.MenuUtils;
import com.danbro.acl.utils.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 权限(AclPermission)表服务实现类
 *
 * @author makejava
 * @since 2021-01-13 14:18:14
 */
@Service("aclPermissionService")
public class AclPermissionServiceImpl extends ServiceImpl<AclPermissionMapper, AclPermission> implements AclPermissionService {
    @Autowired
    AclRolePermissionService rolePermissionService;

    @Autowired
    AclUserService userService;

    @Override
    public List<TreeNodePermissionDto> getAllPermission() {
        List<AclPermission> list = this.list();
        return PermissionUtils.buildPermissionTree(list);
    }

    @Override
    public void removePermissionRecursively(String permissionId) {
        List<TreeNodePermissionDto> allPermission = this.getAllPermission();
        TreeNodePermissionDto node = PermissionUtils.findNodeToRemove(allPermission, permissionId);
        if (node == null) {
            return;
        }
        ArrayList<String> idList = new ArrayList<>();
        PermissionUtils.getRemoveIdList(node, idList);
        this.removeByIds(idList);
    }

    @Override
    public void insertRolePermission(String roleId, String[] permissions) {
        QueryWrapper<AclRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        rolePermissionService.remove(queryWrapper);
        List<AclRolePermission> permissionArrayList = new ArrayList<>();
        for (String permission : permissions) {
            AclRolePermission aclRolePermission = new AclRolePermission().
                    setPermissionId(permission).
                    setRoleId(roleId).
                    setIsDeleted(false);
            permissionArrayList.add(aclRolePermission);
        }
        rolePermissionService.saveBatch(permissionArrayList);
    }

    @Override
    public List<String> getPermissionByUserId(String id) {
        return this.baseMapper.getUserPermissionByUserId(id);
//        List<AclPermission> aclPermissions;
//        if (isSysAdmin(id)) {
//            aclPermissions = this.list();
//        } else {
//            aclPermissions = this.baseMapper.getUserPermissionByUserId(id);
//        }
//        List<TreeNodePermissionDto> nodePermissionDtos = PermissionUtils.buildPermissionTree(aclPermissions);
//        return MenuUtils.bulid(nodePermissionDtos);
    }

    @Override
    public List<TreeNodePermissionDto> getRolePermission(String roleId) {
        List<AclPermission> permissionList = this.baseMapper.getRolePermissionByRoleId(roleId);
        return PermissionUtils.buildPermissionTree(permissionList);
    }

    /**
     * 判断用户是否系统管理员
     *
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        AclUser user = userService.getById(userId);

        if (null != user && "admin".equals(user.getUsername())) {
            return true;
        }
        return false;
    }
}