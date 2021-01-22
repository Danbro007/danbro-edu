package com.danbro.edu.rpcClient;

import com.danbro.vo.OrderVo;
import com.danbro.enums.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author liweimo
 */
@Component
@FeignClient(name = "service-order", fallback = OrderClientFallBack.class)
public interface OrderClient {
    /**
     * 通过会员的ID和课程ID获取会员的订单的信息
     *
     * @param userId   会员ID
     * @param courseId 课程ID
     * @return 订单信息
     */
    @GetMapping("order/info/status/{userId}/{courseId}")
    Result<OrderVo> getOrderInfoByCourseId(@PathVariable String userId, @PathVariable String courseId);
}
