package com.danbro.edu.rpcClient;

import com.danbro.enums.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname VodClient
 * @Description TODO 远程调用 vod 服务的客户端
 * @Date 2021/1/2 18:15
 * @Author Danrbo
 */
@Component
@FeignClient("service-vod")
public interface VodClient {

    /**
     * 通过视频ID删除存储在阿里云的视频
     *
     * @param videoId 视频在阿里云的ID
     * @return 删除结果
     */
    @DeleteMapping("vod/video/{videoId}")
    Result deleteVideoByVideoId(@PathVariable("videoId") String videoId);
}
