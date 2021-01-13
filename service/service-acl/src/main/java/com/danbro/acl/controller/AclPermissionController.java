package com.danbro.acl.controller;

import com.danbro.acl.dto.TreeNodePermissionDto;
import com.danbro.acl.service.AclPermissionService;
import com.danbro.enums.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限(AclPermission)表控制层
 *
 * @author makejava
 * @since 2021-01-13 14:18:14
 */
@RestController
@RequestMapping("acl/permission")
public class AclPermissionController {
    /**
     * 服务对象
     */
    @Resource
    private AclPermissionService aclPermissionService;

    @ApiOperation("查询所有的菜单(树形结构)")
    @GetMapping("")
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

}