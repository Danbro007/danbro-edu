package com.danbro.edu.rpcClient;

import com.danbro.vo.OrderVo;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
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
        throw new MyCustomException(ResultCode.FAILURE);
    }
}
