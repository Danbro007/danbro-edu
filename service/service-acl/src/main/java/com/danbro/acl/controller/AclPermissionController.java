package com.danbro.acl.controller;

import java.util.List;
import javax.annotation.Resource;

import com.danbro.acl.dto.TreeNodePermissionDto;
import com.danbro.acl.service.AclPermissionService;
import com.danbro.acl.service.AclRoleService;
import com.danbro.enums.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限(AclPermission)表控制层
 *
 * @author makejava
 * @since 2021-01-13 14:18:14
 */
@RestController
@RequestMapping("admin/acl/permission")
public class AclPermissionController {
    /**
     * 服务对象
     */
    @Resource
    private AclPermissionService aclPermissionService;

    @Resource
    private AclRoleService aclRoleService;

    @ApiOperation("查询所有的菜单(树形结构)，一般是admin用户。")
    @GetMapping("all")
    public Result<List<TreeNodePermissionDto>> getAllPermission() {
        List<TreeNodePermissionDto> allPermission = aclPermissionService.getAllPermission();
        return Result.ofSuccess(allPermission);
    }

    @ApiOperation("递归删除菜单")
    @DeleteMapping("{permissionId}")
    public Result deletePermission(@PathVariable String permissionId) {
        aclPermissionService.removePermissionRecursively(permissionId);
        return Result.ofSuccess();
    }

    @ApiOperation("给角色分配权限")
    @PostMapping("")
    public Result insertRolePermission(String roleId, String[] permissions) {
        aclPermissionService.insertRolePermission(roleId, permissions);
        return Result.ofSuccess();
    }
//
//    @ApiOperation("添加菜单（权限）")
//    @PostMapping("")
//    public Result insertPermission() {
//        //Todo
//        return Result.ofSuccess();
//    }

    @ApiOperation("查询角色的权限")
    @GetMapping("role/{roleId}")
    public Result<List<TreeNodePermissionDto>> getRolePermission(String roleId) {
        List<TreeNodePermissionDto> permissionList = aclRoleService.getRolePermission(roleId);
        return Result.ofSuccess(permissionList);
    }


}