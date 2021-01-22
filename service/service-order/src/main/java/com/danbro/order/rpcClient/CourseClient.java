package com.danbro.order.rpcClient;

import com.danbro.enums.Result;
import com.danbro.order.rpcClient.fallback.CourseFallback;
import com.danbro.vo.CourseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname CourseClient
 * @Description TODO
 * @Date 2021/1/8 14:19
 * @Author Danrbo
 */
@Component
@FeignClient(value = "service-edu", fallback = CourseFallback.class)
public interface CourseClient {

    @GetMapping("edu/course/info/{courseId}")
    Result<CourseVo> getCourseBasicInfo(@PathVariable String courseId);
}
