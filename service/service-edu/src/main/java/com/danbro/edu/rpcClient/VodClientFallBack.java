package com.danbro.edu.rpcClient;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname VodClientImpl
 * @Description TODO
 * @Date 2021/1/4 12:19
 * @Author Danrbo
 */
@Component
public class VodClientFallBack implements VodClient{
    @Override
    public Result deleteVideoByVideoId(String videoId) {
        return Result.failureOf(ResultCode.DELETE_VIDEO_TIME_OUT);
    }

    @Override
    public Result batchDeleteVideo(List<String> videoList) {
        return Result.failureOf(ResultCode.DELETE_VIDEO_TIME_OUT);
    }
}
