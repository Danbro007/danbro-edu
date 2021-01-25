package com.danbro.msm.controller;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import com.aliyuncs.exceptions.ClientException;
import com.danbro.anotation.ValidParam;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import com.danbro.msm.param.PhoneNumParam;
import com.danbro.msm.service.MsmService;
import com.danbro.utils.RandomUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname MsmController
 * @Description TODO
 * @Date 2021/1/5 14:54
 * @Author Danrbo
 */
@RestController
@Validated
@RequestMapping("msm")
public class MsmController {

    @Resource
    MsmService msmService;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @ValidParam
    @ApiOperation("请求发送短信验证码")
    @PostMapping("captcha")
    public Result sendMessage(@Validated PhoneNumParam phoneNumParam) throws ClientException {
        // redis 有该手机号的验证码则直接返回，不用再次发送验证码。
        if (!StringUtils.isEmpty(redisTemplate.opsForValue().get(phoneNumParam.getMobile()))) {
            return Result.ofSuccess();
        }
        HashMap<String, String> data = new HashMap<>(16);
        data.put("code", RandomUtil.getFourBitRandom());
        System.out.println(data.get("code"));
        // 使用短信平台发送短信验证码
        Boolean success = msmService.sendMessage(phoneNumParam.getMobile(), data);
        // 发送短信成功则会把验证码放入redis缓存里，超时时间为 2 分钟。
        if (success) {
            redisTemplate.opsForValue().set(phoneNumParam.getMobile(), data.get("code"), 2, TimeUnit.MINUTES);
            return Result.ofSuccess();
        } else {
            throw new EduException(ResultCode.SEND_MESSAGE_FAILURE);
        }
    }
}
