package com.danbro.cms.rpcClient;

import java.util.List;

import com.danbro.cms.rpcClient.fallback.TeacherClientFallBack;
import com.danbro.dto.TeacherTopDto;
import com.danbro.enums.Result;
import com.danbro.vo.TeacherVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname TeacherClient
 * @Description TODO
 * @Date 2021/1/4 16:16
 * @Author Danrbo
 */
@FeignClient(name = "service-edu", fallback = TeacherClientFallBack.class)
@Component
public interface TeacherClient {
    /**
     * 获取热门讲师的列表
     *
     * @param limit 热门讲师的数量
     * @return 热门讲师的列表
     */
    @GetMapping("edu/teacher/top/{limit}")
    Result<List<TeacherVo>> getTopTeacherList(@PathVariable String limit);
}
