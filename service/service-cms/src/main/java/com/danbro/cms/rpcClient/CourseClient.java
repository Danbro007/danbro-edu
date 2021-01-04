package com.danbro.cms.rpcClient;

import com.danbro.cms.rpcClient.fallback.CourseClientFallBack;
import com.danbro.enums.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname CourseClient
 * @Description TODO
 * @Date 2021/1/4 16:14
 * @Author Danrbo
 */

@FeignClient(name = "service-edu", fallback = CourseClientFallBack.class)
@Component
public interface CourseClient {
    @GetMapping("edu/course/top/{limit}")
    Result getTopCourseList(@PathVariable String limit);
}
