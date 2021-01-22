package com.danbro.user.center.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.ValidParam;
import com.danbro.dto.UserInfoDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.impl.Insert;
import com.danbro.impl.Select;
import com.danbro.user.center.controller.param.MemberParam;
import com.danbro.vo.MemberVo;
import com.danbro.user.center.service.UcenterMemberService;
import com.danbro.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname UserCenterController
 * @Description TODO 用户中心控制器
 * @Date 2021/1/5 14:55
 * @Author Danrbo
 */
@RestController
@Validated
@RequestMapping("user")
public class UserCenterController {
    @Resource
    UcenterMemberService ucenterMemberService;

    @ValidParam
    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result<String> login(@Validated(Select.class) @RequestBody MemberParam memberParam, BindingResult result) {
        return Result.ofSuccess(ucenterMemberService.login(memberParam));
    }

    @ValidParam
    @ApiOperation("用户注册")
    @PostMapping("register")
    public Result<MemberVo> register(@Validated(Insert.class) @RequestBody MemberParam memberParam, BindingResult result) {
        return Result.ofSuccess(new MemberVo().convertFrom(ucenterMemberService.register(memberParam)));
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

    @ApiOperation("根据会员ID获取会员信息")
    @GetMapping("info/{memberId}")
    public Result<MemberVo> getUserInfo(@IsAssignID(message = "会员ID非法！") @PathVariable String memberId) {
        return Result.ofSuccess(new MemberVo().convertFrom(ucenterMemberService.getMemberInfoByMemberId(memberId)));
    }

    @ApiOperation("通过日期获取那天注册的用户数")
    @GetMapping("statistic/register/{date}")
    public Result<Integer> getRegisterStatistic(@PathVariable String date) {
        return Result.ofSuccess(ucenterMemberService.getRegisterStatisticByDate(date));
    }
}
