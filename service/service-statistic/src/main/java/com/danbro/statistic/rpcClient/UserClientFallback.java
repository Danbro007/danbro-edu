package com.danbro.statistic.rpcClient;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @Classname UserClientFallback
 * @Description TODO
 * @Date 2021/1/12 11:07
 * @Author Danrbo
 */
@Component
public class UserClientFallback implements UserClient {
    @Override
    public Result<Integer> getUserRegisterByDate(String date) {
        throw new MyCustomException(ResultCode.FAILURE);
    }
}
