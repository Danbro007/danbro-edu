package com.danbro.user.center.controller;

import javax.annotation.Resource;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.user.center.dto.WeChatReturnAccessTokenDto;
import com.danbro.user.center.dto.WeChatUserInfoDto;
import com.danbro.user.center.entity.UcenterMember;
import com.danbro.user.center.service.UcenterMemberService;
import com.danbro.user.center.utils.WeChatUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname WeChatUserController
 * @Description TODO 微信用户接口
 * @Date 2021/1/6 13:04
 * @Author Danrbo
 */
@Controller
@RequestMapping("api/ucenter/wx/")

public class WeChatUserController {
    @Resource
    UcenterMemberService ucenterMemberService;

    @ApiOperation("返回微信登录的二维码")
    @GetMapping("login")
    public String login() {
        String loginUrl = WeChatUtils.QR_CODE_URL;
        return String.format("redirect:%s", loginUrl);
    }


    @ApiOperation("返回微信登录的二维码")
    @GetMapping("callback")
    public String callback(String code) {
        if (!StringUtils.isEmpty(code)) {
            String url = String.format(WeChatUtils.TOKEN_URL, WeChatUtils.APP_ID, WeChatUtils.APP_SECRET, code);
            String tokenInfo = HttpUtil.get(url);
            if (!StringUtils.isEmpty(tokenInfo)) {
                WeChatReturnAccessTokenDto tokenDto = JSON.parseObject(tokenInfo, WeChatReturnAccessTokenDto.class);
                if (!StringUtils.isEmpty(tokenDto.getAccessToken()) && !StringUtils.isEmpty(tokenDto.getOpenid())) {
                    String userInfo = HttpUtil.get(String.format(WeChatUtils.WECHAT_USER_INFO_URL, tokenDto.getAccessToken(), tokenDto.getOpenid()));
                    WeChatUserInfoDto weChatUserInfoDto = JSON.parseObject(userInfo, WeChatUserInfoDto.class);
                    UcenterMember ucenterMember = new UcenterMember();
                    BeanUtil.copyProperties(weChatUserInfoDto, ucenterMember);
                    String token = ucenterMemberService.wechatUserLogin(ucenterMember);
                    if (!StringUtils.isEmpty(token)) {
                        return String.format("redirect:http://localhost:3000?token=%s", token);
                    }
                }
            }
        }
        throw new MyCustomException(ResultCode.WECHAT_REGISTER_FAILURE);
    }
}
