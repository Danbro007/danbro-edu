package com.danbro.edu.rpcClient;

import com.danbro.enums.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-user-center", fallback = UCenterUserFallBack.class)
public interface UCenterUserClient {
    @GetMapping("user/info/{userId}")
    Result getUserInfo(@PathVariable String userId);
}
