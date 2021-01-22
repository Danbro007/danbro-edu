package com.danbro.acl.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;

import com.danbro.acl.controller.param.QueryRoleParam;
import com.danbro.acl.controller.param.RoleParam;
import com.danbro.acl.controller.vo.RoleVo;
import com.danbro.acl.service.AclRoleService;
import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.ValidParam;
import com.danbro.enity.OutPutPagingDto;
import com.danbro.enums.Result;
import com.danbro.impl.Insert;
import com.danbro.impl.Update;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * (AclRole)表控制层
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@Validated
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
    public Result<RoleVo> getRoleInfo(@IsAssignID(message = "角色ID不合法！") @PathVariable String roleId) {
        return Result.ofSuccess(new RoleVo().convertFrom(aclRoleService.getRoleInfoById(roleId)));
    }

    @ApiOperation("删除单个角色")
    @DeleteMapping("{roleId}")
    public Result deleteRole(@IsAssignID(message = "角色ID不合法！") @PathVariable String roleId) {
        aclRoleService.deleteRoleById(roleId);
        return Result.ofSuccess();
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping("")
    public Result batchDeleteRole(@RequestBody List<String> roleList) {
        aclRoleService.batchDeleteRole(roleList);
        return Result.ofSuccess();
    }

    @ValidParam
    @ApiOperation("更新角色信息")
    @PutMapping()
    public Result<RoleVo> updateRoleInfo(@Validated(Update.class) @RequestBody RoleParam roleParam, BindingResult result) {
        return Result.ofSuccess(new RoleVo().convertFrom(aclRoleService.insertOrUpdate(roleParam.convertTo())));
    }

    @ValidParam
    @ApiOperation("添加角色信息")
    @PostMapping()
    public Result<RoleVo> insertRole(@Validated(Insert.class) @RequestBody RoleParam roleParam, BindingResult bindingResult) {
        return Result.ofSuccess(new RoleVo().convertFrom(aclRoleService.insertOrUpdate(roleParam.convertTo())));
    }

    @ApiOperation("分页带条件的查询角色信息")
    @PostMapping("list/{page}/{limit}")
    public Result<OutPutPagingDto<RoleVo>> pagingGetRoleListByCondition(@PathVariable String page,
                                                                        @PathVariable String limit,
                                                                        @RequestBody(required = false) QueryRoleParam roleParam) {
        return Result.ofSuccess(aclRoleService.pagingGetRoleListByCondition(Integer.parseInt(page), Integer.parseInt(limit), roleParam));
    }


}