package com.danbro.user.center.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

/**
 * @Classname WeChatUtils
 * @Description TODO
 * @Date 2021/1/6 13:11
 * @Author Danrbo
 */
@Component
public class WeChatUtils implements InitializingBean {

    @Value("${wx.open.app_id}")
    private String appId;

    @Value("${wx.open.app_secret}")
    private String appSecret;

    @Value("${wx.open.redirect_url}")
    private String redirectUrl;

    public static String APP_ID;
    public static String APP_SECRET;
    public static String REDIRECT_URL;
    public static String QR_CODE_URL;
    public static String TOKEN_URL;
    public static String WECHAT_USER_INFO_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = appId;
        APP_SECRET = appSecret;
        REDIRECT_URL = redirectUrl;
        QR_CODE_URL = String.format("https://open.weixin.qq.com/connect/qrconnect?" +
                "appid=%s&" +
                "redirect_uri=%s&" +
                "response_type=code&" +
                "scope=snsapi_login&" +
                "state=atguigu#" +
                "wechat_redirect", APP_ID, URLEncoder.encode(REDIRECT_URL, "utf-8"));
        TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=%s&" +
                "secret=%s&" +
                "code=%s&" +
                "grant_type=authorization_code";

        WECHAT_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=%s" +
                "&openid=%s";
    }
}
