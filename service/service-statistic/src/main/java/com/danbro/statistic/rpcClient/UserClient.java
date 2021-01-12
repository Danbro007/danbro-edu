package com.danbro.statistic.rpcClient;

import com.danbro.enums.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Locale;

/**
 * @Classname UserClient
 * @Description TODO
 * @Date 2021/1/12 11:05
 * @Author Danrbo
 */
@Component
@FeignClient(name = "service-user-center", fallback = UserClientFallback.class)
public interface UserClient {
    /**
     * 远程调用 service-user-center 服务，来查询 date 那天注册的用户
     *
     * @param date 注册的日志
     * @return date 注册的用户数
     */
    @GetMapping("user/statistic/register/{date}")
    Result<Integer> getUserRegisterByDate(@PathVariable String date);
}
