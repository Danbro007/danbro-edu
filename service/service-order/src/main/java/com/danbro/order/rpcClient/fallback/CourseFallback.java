package com.danbro.order.rpcClient.fallback;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
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
        throw new EduException(ResultCode.COURSE_IS_NOT_EXIST);
    }
}
