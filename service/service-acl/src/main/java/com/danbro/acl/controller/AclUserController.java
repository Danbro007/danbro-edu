package com.danbro.acl.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.validation.Valid;

import com.danbro.acl.controller.param.UserParam;
import com.danbro.acl.controller.vo.RoleVo;
import com.danbro.acl.controller.vo.UserVo;
import com.danbro.acl.service.AclUserService;
import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.IsPositiveNum;
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
 * 用户表(AclUser)表控制层
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@Validated
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
    public Result<UserVo> getUserInfo(@IsAssignID(message = "用户ID非法！") @PathVariable String userId) {
        return Result.ofSuccess(aclUserService.getUserInfoById(userId));
    }

    @ApiOperation("删除单个用户")
    @DeleteMapping("{userId}")
    public Result deleteUser(@IsAssignID(message = "用户ID非法！") @PathVariable String userId) {
        aclUserService.deleteUserById(userId);
        return Result.ofSuccess();
    }

    @ApiOperation("批量删除用户")
    @DeleteMapping("")
    public Result batchDeleteUser(@RequestBody List<String> userList) {
        aclUserService.batchDeleteUser(userList);
        return Result.ofSuccess();
    }

    @ValidParam
    @ApiOperation("更新用户")
    @PutMapping()
    public Result<UserVo> updateUser(@Validated(Update.class) @RequestBody UserParam userParam, BindingResult result) {
        return Result.ofSuccess(new UserVo().convertFrom(aclUserService.insertOrUpdateUser(userParam.convertTo())));
    }

    @ValidParam
    @ApiOperation("添加用户")
    @PostMapping()
    public Result<UserVo> insertUser(@Validated(Insert.class) @RequestBody UserParam userParam, BindingResult result) {
        return Result.ofSuccess(new UserVo().convertFrom(aclUserService.insertOrUpdateUser(userParam.convertTo())));
    }

    @ApiOperation("分页带条件的查询角色信息")
    @PostMapping("{page}/{limit}")
    public Result<OutPutPagingDto<UserVo>> pagingGetUserListByCondition(@IsPositiveNum(message = "page参数非法！") @PathVariable String page,
                                                                        @IsPositiveNum(message = "limit参数非法！") @PathVariable String limit,
                                                                        @RequestBody(required = false) UserParam userParam) {
        return Result.ofSuccess(aclUserService.pagingGetUserListByCondition(Integer.parseInt(page), Integer.parseInt(limit), userParam));
    }

    @ApiOperation("查找用户的角色")
    @GetMapping("role/{userId}")
    public Result<Map<String, List<RoleVo>>> getUserRoleListByUserId(@PathVariable String userId) {
        return Result.ofSuccess(aclUserService.getUserRoleListByUserId(userId));
    }

    @ApiOperation("添加用户的角色")
    @PostMapping("role")
    public Result insertUserRole(@IsAssignID(message = "用户ID非法！") @RequestParam String userId,
                                 @IsAssignID(message = "角色ID非法！") @RequestParam String roleId) {
        aclUserService.inertUserRole(userId, roleId);
        return Result.ofSuccess();
    }

}