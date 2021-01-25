package com.danbro.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import com.danbro.oss.service.OssService;
import com.danbro.oss.utils.OssClientUtils;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
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

    @Override
    public String uploadAvatar(MultipartFile file, String type) throws IOException {
        OSS ossClient = getOssClient();
        String uploadFileName;
        try {
            uploadFileName = OssClientUtils.getUploadFileName(file.getOriginalFilename(), type);
            ossClient.putObject(OssClientUtils.BUCKET_NAME, uploadFileName, file.getInputStream());
            return uploadFileName;
        } catch (ClientException e) {
            throw new EduException(ResultCode.OSS_CLIENT_CONNECTION_ERROR);
        } finally {
            ossClient.shutdown();
        }
    }

    /**
     * 创建一个 oss 客户端
     *
     * @return oss 客户端
     */
    private OSS getOssClient() {
        return new OSSClientBuilder().build(OssClientUtils.END_POINT, OssClientUtils.ACCESS_KEY_ID, OssClientUtils.ACCESS_KEY_SECRET);
    }
}
