package com.danbro.edu.rpcClient;

import com.danbro.dto.OrderDto;
import com.danbro.enums.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-order", fallback = OrderClientFallBack.class)
public interface OrderClient {

    @GetMapping("order/info/status/{userId}/{courseId}")
    Result<OrderDto> getOrderInfoByCourseId(@PathVariable String userId, @PathVariable String courseId);
}
