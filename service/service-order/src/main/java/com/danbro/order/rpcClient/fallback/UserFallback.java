package com.danbro.order.rpcClient.fallback;

import com.danbro.dto.UcenterMemberInfoDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
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
    public Result<UcenterMemberInfoDto> getUserInfo(String userId) {
        throw new MyCustomException(ResultCode.USER_SERVICE_TIME_OUT);
    }
}
