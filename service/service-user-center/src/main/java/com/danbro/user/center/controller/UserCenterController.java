package com.danbro.user.center.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.user.center.dto.UserLoginDto;
import com.danbro.user.center.dto.UserRegisterDto;
import com.danbro.user.center.entity.UcenterMember;
import com.danbro.user.center.service.UcenterMemberService;
import com.danbro.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("edu")
public class UserCenterController {
    @Resource
    UcenterMemberService ucenterMemberService;

    @ApiOperation("用户登录")
    @PostMapping("user/login")
    public Result login(@RequestBody UserLoginDto user, BindingResult result) {
        if (result.hasErrors()) {
            return Result.failureOf(ResultCode.FAILURE, "errors", result.getAllErrors());
        }
        String token = ucenterMemberService.login(user);
        return Result.successOf("token", token);
    }

    @ApiOperation("用户注册")
    @PostMapping("user/register")
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
    @GetMapping("user/info")
    public Result getUserInfo(HttpServletRequest request) {
        String id = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(id)) {
            return Result.failureOf(ResultCode.FAILURE);
        }
        UcenterMember member = ucenterMemberService.getById(id);
        return Result.successOf("user", member);
    }


}
