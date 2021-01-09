package com.danbro.edu.rpcClient;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import org.springframework.stereotype.Component;

/**
 * @Classname UCenterUserFallBack
 * @Description TODO
 * @Date 2021/1/7 23:17
 * @Created by Administrator
 */
@Component
public class UCenterUserFallBack implements UCenterUserClient {
    @Override
    public Result getUserInfo(String userId) {
        return Result.ofFail(ResultCode.USER_NOT_EXIST);
    }
}
