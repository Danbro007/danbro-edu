package com.danbro.oss.service;

import com.aliyun.oss.model.PutObjectResult;
import com.danbro.enums.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Classname OssService
 * @Description TODO
 * @Date 2020/12/17 12:23
 * @Author Danrbo
 */

public interface OssService {

    /**
     * 上传头像
     * @param file 头像文件
     * @return 上传后头像的url
     */
    String uploadAvatar(MultipartFile file,String type) throws IOException;
}
