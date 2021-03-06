package com.danbro.cms.rpcClient;

import java.util.List;
import com.danbro.cms.rpcClient.fallback.CourseClientFallBack;
import com.danbro.dto.CourseTopDto;
import com.danbro.enums.Result;
import com.danbro.vo.CourseVo;
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
    /**
     * 远程调用 Edu 服务获取排名前几名的热门课程
     *
     * @param limit
     * @return 热门课程列表
     */
    @GetMapping("edu/course/top/{limit}")
    Result<List<CourseVo>> getTopCourseList(@PathVariable String limit);
}
