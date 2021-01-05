package com.danbro.msm.controller;

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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Classname MsmController
 * @Description TODO
 * @Date 2021/1/5 14:54
 * @Author Danrbo
 */
@RestController
@CrossOrigin
@RequestMapping("edu")
public class MsmController {

    @Resource
    MsmService msmService;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @ApiOperation("请求发送短信验证码")
    @PostMapping("msm")
    public Result sendMessage(@Valid @RequestBody PhoneNumDto phoneNumDto, BindingResult result) {
        if (result.hasErrors()) {
            return Result.failureOf(ResultCode.FAILURE, "errors", result.getAllErrors());
        }
        if (!StringUtils.isEmpty(redisTemplate.opsForValue().get(phoneNumDto.getPhone()))) {
            return Result.successOf();
        }
        HashMap<String, String> data = new HashMap<>(16);
        data.put("code", RandomUtil.getFourBitRandom());
        try {
            Boolean isSuccess = msmService.sendMessage(phoneNumDto.getPhone(), data);
            if (isSuccess) {
                redisTemplate.opsForValue().set(phoneNumDto.getPhone(), data.get("code"), 5, TimeUnit.MINUTES);
                return Result.successOf();
            }
            throw new MyCustomException(ResultCode.SEND_MESSAGE_FAILURE);
        } catch (ClientException e) {
            throw new MyCustomException(ResultCode.SEND_MESSAGE_FAILURE);
        }
    }
}
