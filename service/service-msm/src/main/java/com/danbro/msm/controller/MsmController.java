package com.danbro.msm.controller;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.validation.Valid;
import com.aliyuncs.exceptions.ClientException;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.msm.dto.PhoneNumDto;
import com.danbro.msm.service.MsmService;
import com.danbro.utils.RandomUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname MsmController
 * @Description TODO
 * @Date 2021/1/5 14:54
 * @Author Danrbo
 */
@RestController

@RequestMapping("msm")
public class MsmController {

    @Resource
    MsmService msmService;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @ApiOperation("请求发送短信验证码")
    @PostMapping("captcha")
    public Result sendMessage(@Valid @RequestBody PhoneNumDto phoneNumDto, BindingResult result) {
        if (!StringUtils.isEmpty(redisTemplate.opsForValue().get(phoneNumDto.getMobile()))) {
            return Result.ofSuccess();
        }
        HashMap<String, String> data = new HashMap<>(16);
        data.put("code", RandomUtil.getFourBitRandom());
        System.out.println(data.get("code"));
        try {
            Boolean isSuccess = msmService.sendMessage(phoneNumDto.getMobile(), data);
            if (isSuccess) {
                redisTemplate.opsForValue().set(phoneNumDto.getMobile(), data.get("code"), 2, TimeUnit.MINUTES);
                return Result.ofSuccess();
            }
            throw new MyCustomException(ResultCode.SEND_MESSAGE_FAILURE);
        } catch (ClientException e) {
            throw new MyCustomException(ResultCode.SEND_MESSAGE_FAILURE);
        }
    }
}
