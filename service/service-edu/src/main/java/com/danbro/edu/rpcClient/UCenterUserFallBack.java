package com.danbro.edu.rpcClient;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
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
    public Result getMemberInfoByMemberId(String memberId) {
        throw new MyCustomException(ResultCode.USER_SERVICE_TIME_OUT);
    }
}
