package com.danbro.user.center.controller;


import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.user.center.dto.UserLoginDto;
import com.danbro.user.center.dto.UserRegisterDto;
import com.danbro.user.center.entity.UcenterMember;
import com.danbro.user.center.service.UcenterMemberService;
import com.danbro.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname UserCenterController
 * @Description TODO 用户中心控制器
 * @Date 2021/1/5 14:55
 * @Author Danrbo
 */
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserCenterController {
    @Resource
    UcenterMemberService ucenterMemberService;

    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result login(@RequestBody UserLoginDto user, BindingResult result) {
        if (result.hasErrors()) {
            return Result.failureOf(ResultCode.FAILURE, "errors", result.getAllErrors());
        }
        String token = ucenterMemberService.login(user);
        return Result.successOf("token", token);
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    public Result register(@RequestBody UserRegisterDto user, BindingResult result) {
        if (result.hasErrors()) {
            return Result.failureOf(ResultCode.FAILURE, "errors", result.getAllErrors());
        }
        if (ucenterMemberService.register(user)) {
            return Result.successOf();
        } else {
            return Result.failureOf(ResultCode.RESISTER_FAILURE);
        }
    }

    @ApiOperation("根据token获取用户信息")
    @GetMapping("info")
    public Result getUserInfo(HttpServletRequest request) {
        String id = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(id)) {
            return Result.failureOf(ResultCode.FAILURE);
        }
        UcenterMember member = ucenterMemberService.getById(id);
        return Result.successOf("userInfo", member);
    }
}
