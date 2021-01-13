package com.danbro.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.acl.dto.TreeNodePermissionDto;
import com.danbro.acl.entity.AclPermission;
import com.danbro.acl.mapper.AclPermissionMapper;
import com.danbro.acl.service.AclPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return buildPermissionTree(list);
    }

    /**
     * 构建树形结构的权限列表
     *
     * @param list 需要构建的列表
     * @return 树形结构的权限列表
     */
    private List<TreeNodePermissionDto> buildPermissionTree(List<AclPermission> list) {
        List<TreeNodePermissionDto> rootNodeList = new ArrayList<>();
        for (AclPermission aclPermission : list) {
            // 第一级
            if ("0".equals(aclPermission.getPid())) {
                aclPermission.setLevel(1);
                TreeNodePermissionDto permissionDto = new TreeNodePermissionDto();
                BeanUtils.copyProperties(aclPermission, permissionDto);
                rootNodeList.add(setChildren(permissionDto, list));
            }
        }
        return rootNodeList;
    }

    private TreeNodePermissionDto setChildren(TreeNodePermissionDto fatherNode, List<AclPermission> list) {
        fatherNode.setChildren(new ArrayList<>());
        // 如果子节点的 pid 与父节点的 id 相同则把这个子节点加入到父节点的 children 里
        for (AclPermission sonNode : list) {
            if (fatherNode.getId().equals(sonNode.getPid())) {
                // 子节点 level 加 1
                sonNode.setLevel(fatherNode.getLevel() + 1);
                TreeNodePermissionDto permissionDto = new TreeNodePermissionDto();
                BeanUtils.copyProperties(sonNode, permissionDto);
                // 递归调用
                fatherNode.getChildren().add(setChildren(permissionDto, list));
            }
        }
        return fatherNode;
    }
}