package com.danbro.order.rpcClient.fallback;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import com.danbro.order.rpcClient.UserClient;
import org.springframework.stereotype.Component;

/**
 * @Classname UserFallback
 * @Description TODO
 * @Date 2021/1/8 14:24
 * @Author Danrbo
 */
@Component
public class UserFallback implements UserClient {
    @Override
    public Result getMemberInfoByMemberId(String memberId) {
        throw new EduException(ResultCode.USER_SERVICE_TIME_OUT);
    }
}
