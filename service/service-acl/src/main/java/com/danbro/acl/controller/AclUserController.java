package com.danbro.acl.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.validation.Valid;
import com.danbro.acl.dto.AclRoleDto;
import com.danbro.acl.dto.AclUserDto;
import com.danbro.acl.service.AclUserService;
import com.danbro.enity.OutPutPagingDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表(AclUser)表控制层
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@RestController
@RequestMapping("admin/acl/user")
public class AclUserController {
    /**
     * 服务对象
     */
    @Resource
    private AclUserService aclUserService;

    @ApiOperation("获取某个用户基本信息")
    @GetMapping("{userId}")
    public Result<AclUserDto> getUserInfo(@PathVariable String userId) {
        return Result.ofSuccess(aclUserService.getUserInfoById(userId));
    }

    @ApiOperation("删除单个用户")
    @DeleteMapping("{userId}")
    public Result deleteUser(@PathVariable String userId) {
        aclUserService.deleteUserById(userId);
        return Result.ofSuccess();
    }

    @ApiOperation("批量删除用户")
    @DeleteMapping("")
    public Result batchDeleteUser(@RequestBody List<String> userList) {
        aclUserService.batchDeleteUser(userList);
        return Result.ofSuccess();
    }

    @ApiOperation("更新角色信息")
    @PutMapping()
    public Result<AclUserDto> updateUserInfo(@RequestBody AclUserDto aclUserDto) {
        return Result.ofSuccess(aclUserService.insertOrUpdate(aclUserDto));
    }

    @ApiOperation("添加用户")
    @PostMapping()
    public Result<AclUserDto> insertUser(@Valid @RequestBody AclUserDto aclUserDto, BindingResult bindingResult) {
        return Result.ofSuccess(aclUserService.insertOrUpdate(aclUserDto));
    }

    @ApiOperation("分页带条件的查询角色信息")
    @PostMapping("{page}/{limit}")
    public Result<OutPutPagingDto<AclUserDto>> pagingGetUserListByCondition(@PathVariable Integer page,
                                                                            @PathVariable Integer limit,
                                                                            @RequestBody(required = false) AclUserDto queryCondition) {
        return Result.ofSuccess(aclUserService.pagingGetUserListByCondition(page, limit, queryCondition));
    }

    @ApiOperation("查找用户的角色")
    @GetMapping("role/{userId}")
    public Result<Map<String,List<AclRoleDto>>> getUserRoleListByUserId(@PathVariable String userId) {
        return Result.ofSuccess(aclUserService.getUserRoleListByUserId(userId));
    }

    @ApiOperation("添加用户的角色")
    @PostMapping("role")
    public Result insertUserRole(@RequestParam String userId,
                                 @RequestParam String roleId) {
        aclUserService.inertUserRole(userId, roleId);
        return Result.ofSuccess();
    }

}