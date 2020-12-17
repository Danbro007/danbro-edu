package com.danbro.oss.service.impl;

import cn.hutool.core.util.URLUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.oss.service.OssService;
import com.danbro.oss.utils.OssClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Classname OssServiceImpl
 * @Description TODO
 * @Date 2020/12/17 12:25
 * @Author Danrbo
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {
    private final static String AVATAR = "avatar";

    @Override
    public String uploadAvatar(MultipartFile file) throws IOException {
        OSS ossClient = getOssClient();
        String uploadFileName;
        try {
            uploadFileName = OssClientUtils.getUploadFileName(file.getOriginalFilename(), AVATAR);
            ossClient.putObject(
                    OssClientUtils.BUCKET_NAME,
                    uploadFileName,
                    file.getInputStream());
        } finally {
            ossClient.shutdown();
        }
        return uploadFileName;
    }

    private OSS getOssClient() {
        return new OSSClientBuilder().build(OssClientUtils.END_POINT, OssClientUtils.ACCESS_KEY_ID, OssClientUtils.ACCESS_KEY_SECRET);
    }
}
