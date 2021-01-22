package com.danbro.acl.utils;

import java.util.ArrayList;
import java.util.List;

import com.danbro.acl.controller.vo.TreeNodePermissionVo;
import com.danbro.acl.entity.AclPermission;
import org.springframework.beans.BeanUtils;

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
    public static List<TreeNodePermissionVo> buildPermissionTree(List<AclPermission> list) {
        List<TreeNodePermissionVo> rootNodeList = new ArrayList<>();
        for (AclPermission aclPermission : list) {
            TreeNodePermissionVo permissionVo = new TreeNodePermissionVo();
            BeanUtils.copyProperties(aclPermission, permissionVo);
            // 第一级
            if ("0".equals(aclPermission.getPid())) {
                permissionVo.setLevel(1);
                rootNodeList.add(setChildren(permissionVo, list));
            }

        }
        return rootNodeList;
    }

    private static TreeNodePermissionVo setChildren(TreeNodePermissionVo fatherNode, List<AclPermission> list) {
        fatherNode.setChildren(new ArrayList<>());
        // 如果子节点的 pid 与父节点的 id 相同则把这个子节点加入到父节点的 children 里
        for (AclPermission sonNode : list) {
            if (fatherNode.getId().equals(sonNode.getPid())) {
                // 子节点 level 加 1
                TreeNodePermissionVo childNode = new TreeNodePermissionVo();
                BeanUtils.copyProperties(sonNode, childNode);
                childNode.setLevel(fatherNode.getLevel() + 1);
                // 递归调用
                fatherNode.getChildren().add(setChildren(childNode, list));
            }
        }
        return fatherNode;
    }

    /**
     * 找到要删除的权限
     *
     * @param allPermission 所有的权限
     * @param permissionId  权限Id
     * @return 要删除的权限
     */
    public static TreeNodePermissionVo findNodeToRemove(List<TreeNodePermissionVo> allPermission, String permissionId) {
        for (TreeNodePermissionVo permission : allPermission) {
            if (permission.getId().equals(permissionId)) {
                return permission;
            } else if (permission.getChildren().size() > 0) {
                TreeNodePermissionVo nodeToRemove = findNodeToRemove(permission.getChildren(), permissionId);
                if (nodeToRemove == null) {
                    continue;
                }
                return nodeToRemove;
            }
        }
        return null;
    }

    /**
     * 获取要删除权限的ID
     *
     * @param node 删除的权限
     * @param list 删除的权限 ID 列表
     */
    public static void getRemoveIdList(TreeNodePermissionVo node, List<String> list) {
        list.add(node.getId());
        if (node.getChildren() != null && node.getChildren().size() > 0) {
            for (TreeNodePermissionVo child : node.getChildren()) {
                getRemoveIdList(child, list);
            }
        }
    }
}
