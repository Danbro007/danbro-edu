package com.danbro.edu.controller;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname UserController
 * @Description TODO 用户登录控制器
 * @Date 2020/12/16 12:05
 * @Author Danrbo
 */
@RestController
@CrossOrigin
@RequestMapping("edu/user")
public class UserController {

    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result login() {
        return Result.successOf(ResultCode.SUCCESS, "token", "admin");
    }

    @ApiOperation("返回用户信息")
    @GetMapping("info")
    public Result info() {
        return Result.successOf(ResultCode.SUCCESS).
                setDataChain("roles", "[admin]").
                setDataChain("name", "admin").
                setDataChain("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
