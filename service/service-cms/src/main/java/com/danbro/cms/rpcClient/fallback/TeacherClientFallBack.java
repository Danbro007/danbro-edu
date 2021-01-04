package com.danbro.cms.rpcClient.fallback;

import com.danbro.cms.rpcClient.CourseClient;
import com.danbro.cms.rpcClient.TeacherClient;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import org.springframework.stereotype.Component;

/**
 * @Classname TeacherClientFallBack
 * @Description TODO
 * @Date 2021/1/4 16:24
 * @Author Danrbo
 */
@Component
public class TeacherClientFallBack implements TeacherClient {

    @Override
    public Result getTopTeacherList(String limit) {
        return Result.failureOf(ResultCode.GET_TOP_TEACHER_LIST);
    }
}
