package com.danbro.order.rpcClient.fallback;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.RpcClientException;
import com.danbro.order.rpcClient.CourseClient;
import org.springframework.stereotype.Component;

/**
 * @Classname CourseFallback
 * @Description TODO
 * @Date 2021/1/8 14:25
 * @Author Danrbo
 */
@Component
public class CourseFallback implements CourseClient {
    @Override
    public Result getCourseBasicInfo(String courseId) {
        throw new RpcClientException(ResultCode.COURSE_SERVICE_TIME_OUT);
    }
}
