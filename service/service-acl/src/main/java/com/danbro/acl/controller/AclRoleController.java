package com.danbro.acl.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import com.danbro.acl.dto.AclRoleDto;
import com.danbro.acl.dto.TreeNodePermissionDto;
import com.danbro.acl.service.AclRoleService;
import com.danbro.enity.OutPutPagingDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * (AclRole)表控制层
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@RestController
@RequestMapping("admin/acl/role")
public class AclRoleController {
    /**
     * 服务对象
     */
    @Resource
    private AclRoleService aclRoleService;

    @ApiOperation("获取某个角色信息")
    @GetMapping("{roleId}")
    public Result<AclRoleDto> getRoleInfo(@PathVariable String roleId) {
        return Result.ofSuccess(aclRoleService.getRoleInfoById(roleId));
    }

    @ApiOperation("删除单个角色")
    @DeleteMapping("{roleId}")
    public Result deleteRole(@PathVariable String roleId) {
        aclRoleService.deleteRoleById(roleId);
        return Result.ofSuccess();
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping("")
    public Result batchDeleteRole(@RequestBody List<String> roleList) {
        aclRoleService.batchDeleteRole(roleList);
        return Result.ofSuccess();
    }

    @ApiOperation("更新角色信息")
    @PutMapping()
    public Result<AclRoleDto> updateRoleInfo(@RequestBody AclRoleDto aclRoleDto) {
        return Result.ofSuccess(aclRoleService.insertOrUpdate(aclRoleDto));
    }

    @ApiOperation("添加角色信息")
    @PostMapping()
    public Result<AclRoleDto> insertRole(@Valid @RequestBody AclRoleDto aclRoleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyCustomException(ResultCode.PARAMS_ERROR, bindingResult.getAllErrors());
        }
        return Result.ofSuccess(aclRoleService.insertOrUpdate(aclRoleDto));
    }

    @ApiOperation("分页带条件的查询角色信息")
    @PostMapping("list/{page}/{limit}")
    public Result<OutPutPagingDto<AclRoleDto>> pagingGetRoleListByCondition(@PathVariable Integer page,
                                                                            @PathVariable Integer limit,
                                                                            @RequestBody(required = false) AclRoleDto queryCondition) {
        return Result.ofSuccess(aclRoleService.pagingGetRoleListByCondition(page, limit, queryCondition));
    }


}