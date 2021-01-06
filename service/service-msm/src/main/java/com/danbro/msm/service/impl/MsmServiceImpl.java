package com.danbro.msm.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.danbro.msm.service.MsmService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Classname MsmServiceImpl
 * @Description TODO
 * @Date 2021/1/5 14:55
 * @Author Danrbo
 */
@Service("msmService")
public class MsmServiceImpl implements MsmService {

    @Value("${aliyun.msm.product}")
    private String product;

    @Value("${aliyun.msm.domain}")
    private String domain;

    @Value("${aliyun.msm.message.code}")
    private String messageCode;

    @Value("${aliyun.msm.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.msm.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.msm.signName}")
    private String signName;
    @Value("${aliyun.msm.outId}")
    private String outId;

    @Override
    public Boolean sendMessage(String phone, HashMap<String, String> message) throws ClientException {
//        //设置超时时间-可自行调整
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//        //初始化ascClient,暂时不支持多region（请勿修改）
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//        IAcsClient acsClient = new DefaultAcsClient(profile);
//        SendSmsRequest request = new SendSmsRequest();
//        request.setMethod(MethodType.POST);
//        request.setPhoneNumbers(phone);
//        request.setSignName(signName);
//        request.setTemplateCode(messageCode);
//        request.setTemplateParam(JSON.toJSONString(message));
//        request.setOutId(outId);
//        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//            return true;
//        }
//        return false;
        return true;
    }
}
