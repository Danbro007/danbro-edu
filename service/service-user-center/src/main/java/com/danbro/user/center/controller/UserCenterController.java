package com.danbro.user.center.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.danbro.dto.UcenterMemberInfoDto;
import com.danbro.dto.UserInfoDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.user.center.dto.UserLoginDto;
import com.danbro.user.center.dto.UserRegisterDto;
import com.danbro.user.center.entity.UcenterMember;
import com.danbro.user.center.service.UcenterMemberService;
import com.danbro.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public Result<String> login(@RequestBody UserLoginDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new MyCustomException(ResultCode.PARAMS_ERROR, result.getAllErrors());
        }
        String token = ucenterMemberService.login(user);
        return Result.ofSuccess(token);
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    public Result register(@RequestBody UserRegisterDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new MyCustomException(ResultCode.PARAMS_ERROR, result.getAllErrors());
        }
        if (ucenterMemberService.register(user)) {
            return Result.ofSuccess();
        } else {
            return Result.ofFail(ResultCode.RESISTER_FAILURE);
        }
    }

    @ApiOperation("根据token获取用户信息")
    @GetMapping("info")
    public Result<UserInfoDto> getUserInfo(HttpServletRequest request) {
        UserInfoDto userInfo = JwtUtils.getMemberIdByJwtToken(request);
        if (userInfo == null) {
            throw new MyCustomException(ResultCode.USER_NOT_EXIST);
        }
        return Result.ofSuccess(userInfo);
    }

    @ApiOperation("根据用户ID获取用户信息")
    @GetMapping("info/{userId}")
    public Result<UcenterMemberInfoDto> getUserInfo(@PathVariable String userId) {
        UcenterMember member = ucenterMemberService.getById(userId);
        UcenterMemberInfoDto memberInfoDto = new UcenterMemberInfoDto();
        if (member == null) {
            throw new MyCustomException(ResultCode.USER_NOT_EXIST);
        }
        BeanUtils.copyProperties(member, memberInfoDto);
        return Result.ofSuccess(memberInfoDto);
    }

    @ApiOperation("通过日期获取那天注册的用户数")
    @GetMapping("statistic/register/{date}")
    public Result<Integer> getRegisterStatistic(@PathVariable String date) {
        Integer userNum = ucenterMemberService.getRegisterStatisticByDate(date);
        return Result.ofSuccess(userNum);
    }
}
