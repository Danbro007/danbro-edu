package com.danbro.user.center.controller;


import javax.annotation.Resource;
import com.danbro.enums.Result;
import com.danbro.user.center.dto.UserLoginDto;
import com.danbro.user.center.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public Result login(@RequestBody UserLoginDto user) {
        String token = ucenterMemberService.login(user);
        return Result.successOf("token", token);
    }

}
