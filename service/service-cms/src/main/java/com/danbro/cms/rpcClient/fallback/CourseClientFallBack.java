package com.danbro.cms.rpcClient.fallback;

import com.danbro.cms.rpcClient.CourseClient;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import org.springframework.stereotype.Component;

/**
 * @Classname CourseClientFallBack
 * @Description TODO
 * @Date 2021/1/4 16:21
 * @Author Danrbo
 */
@Component
public class CourseClientFallBack implements CourseClient {
    @Override
    public Result getTopCourseList(String limit) {
        return Result.failureOf(ResultCode.GET_TOP_COURSE_LIST);
    }
}