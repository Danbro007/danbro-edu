package com.danbro.edu.rpcClient;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.RpcClientException;
import com.danbro.vo.OrderVo;
import org.springframework.stereotype.Component;

/**
 * @author liweimo
 * @Classname OrderClientFallBack
 * @Description TODO
 * @Date 2021/1/11 22:11
 * @Created by Administrator
 */
@Component
public class OrderClientFallBack implements OrderClient {
    @Override
    public Result<OrderVo> getOrderInfoByCourseId(String userId, String courseId) {
        throw new RpcClientException(ResultCode.ORDER_SERVICE_TIME_OUT);
    }
}
