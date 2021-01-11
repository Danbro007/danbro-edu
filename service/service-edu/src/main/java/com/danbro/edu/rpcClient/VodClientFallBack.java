package com.danbro.edu.rpcClient;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname VodClientImpl
 * @Description TODO
 * @Date 2021/1/4 12:19
 * @Author Danrbo
 */
@Component
public class VodClientFallBack implements VodClient {
    @Override
    public Result deleteVideoByVideoId(String videoId) {
        throw new MyCustomException(ResultCode.VOD_SERVICE_TIME_OUT);
    }

    @Override
    public Result batchDeleteVideo(List<String> videoList) {
        throw new MyCustomException(ResultCode.VOD_SERVICE_TIME_OUT);
    }
}
