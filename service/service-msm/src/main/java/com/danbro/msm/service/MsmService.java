package com.danbro.msm.service;

import com.aliyuncs.exceptions.ClientException;

import java.util.HashMap;

/**
 * @Classname MsmService
 * @Description TODO
 * @Date 2021/1/5 14:55
 * @Author Danrbo
 */
public interface MsmService {
    /**
     * 通过手机号向阿里云请求发送验证码
     * @param phone 手机号
     * @return 发送结果
     */
    Boolean sendMessage(String phone, HashMap<String,String> message) throws ClientException;
}
