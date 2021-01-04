package com.danbro.vod.utils;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 * @Classname VodAliYunUtils
 * @Description TODO
 * @Date 2020/12/31 16:31
 * @Author Danrbo
 */
@Component
@Data
public class VodAliYunUtils {
    @Value("${aliyun.vod.keyid}")
    private String accessKeyId;

    @Value("${aliyun.vod.keysecret}")
    private String accessKeySecret;

    /**
     * 点播服务接入区域
     */
    private static final String REGION_ID = "cn-shanghai";


    public DefaultAcsClient initVodClient() throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }

}