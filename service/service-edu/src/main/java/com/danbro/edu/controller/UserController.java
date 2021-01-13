package com.danbro.edu.controller;

import com.danbro.edu.dto.AdminUserLoginInfoDto;
import com.danbro.enums.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname UserController
 * @Description TODO 用户登录控制器
 * @Date 2020/12/16 12:05
 * @Author Danrbo
 */
@RestController

@RequestMapping("edu/user")
public class UserController {

    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result<String> login() {
        return Result.ofSuccess("admin");
    }

    @ApiOperation("返回用户信息")
    @GetMapping("info")
    public Result<AdminUserLoginInfoDto> info() {
        AdminUserLoginInfoDto admin = new AdminUserLoginInfoDto().
                setRoles("[admin]").
                setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif").
                setName("admin");
        return Result.ofSuccess(admin);
    }
}
