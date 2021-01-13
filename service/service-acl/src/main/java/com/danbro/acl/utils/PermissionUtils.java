package com.danbro.acl.utils;

import com.danbro.acl.dto.TreeNodePermissionDto;
import com.danbro.acl.entity.AclPermission;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PermissionUtils
 * @Description TODO 权限工具类
 * @Date 2021/1/13 15:39
 * @Author Danrbo
 */
public class PermissionUtils {
    /**
     * 构建树形结构的权限列表
     *
     * @param list 需要构建的列表
     * @return 树形结构的权限列表
     */
    public static List<TreeNodePermissionDto> buildPermissionTree(List<AclPermission> list) {
        List<TreeNodePermissionDto> rootNodeList = new ArrayList<>();
        for (AclPermission aclPermission : list) {
            // 第一级
            if ("0".equals(aclPermission.getPid())) {
                TreeNodePermissionDto permissionDto = new TreeNodePermissionDto();
                BeanUtils.copyProperties(aclPermission, permissionDto);
                permissionDto.setLevel(1);
                rootNodeList.add(setChildren(permissionDto, list));
            }
        }
        return rootNodeList;
    }

    private static TreeNodePermissionDto setChildren(TreeNodePermissionDto fatherNode, List<AclPermission> list) {
        fatherNode.setChildren(new ArrayList<>());
        // 如果子节点的 pid 与父节点的 id 相同则把这个子节点加入到父节点的 children 里
        for (AclPermission sonNode : list) {
            if (fatherNode.getId().equals(sonNode.getPid())) {
                // 子节点 level 加 1
                TreeNodePermissionDto permissionDto = new TreeNodePermissionDto();
                BeanUtils.copyProperties(sonNode, permissionDto);
                permissionDto.setLevel(fatherNode.getLevel() + 1);
                // 递归调用
                fatherNode.getChildren().add(setChildren(permissionDto, list));
            }
        }
        return fatherNode;
    }
}