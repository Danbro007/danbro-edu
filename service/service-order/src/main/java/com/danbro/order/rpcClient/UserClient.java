package com.danbro.order.rpcClient;

import com.danbro.dto.UcenterMemberInfoDto;
import com.danbro.enums.Result;
import com.danbro.order.rpcClient.fallback.UserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname UserClient
 * @Description TODO
 * @Date 2021/1/8 14:16
 * @Author Danrbo
 */
@Component
@FeignClient(value = "service-user-center", fallback = UserFallback.class)
public interface UserClient {

    @GetMapping("user/info/{userId}")
    UcenterMemberInfoDto getUserInfo(@PathVariable String userId);
}
