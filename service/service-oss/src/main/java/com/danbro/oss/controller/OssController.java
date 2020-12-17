package com.danbro.oss.controller;

import cn.hutool.core.util.URLUtil;
import com.aliyun.oss.model.PutObjectResult;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.oss.service.OssService;
import com.danbro.oss.utils.OssClientUtils;
import com.danbro.oss.utils.UploadDir;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname OssController
 * @Description TODO
 * @Date 2020/12/17 12:37
 * @Author Danrbo
 */
@RestController
@RequestMapping("upload")
@Slf4j
@CrossOrigin
public class OssController {

    @Autowired
    OssService ossService;

    @ApiOperation("上传头像")
    @PostMapping("avatar")
    public Result uploadAvatar(MultipartFile avatar) {
        if (avatar.getSize() > OssClientUtils.UPLOAD_FILE_SIZE_LIMIT) {
            throw new MyCustomException(ResultCode.UPLOAD_FILE_OVER_SIZE);
        }
        try {
            String avatarUrl = ossService.uploadAvatar(avatar);
            if (avatarUrl != null && !StringUtils.isEmpty(avatarUrl)) {
                return Result.successOf(ResultCode.SUCCESS, "avatar", String.format("https://%s.%s/%s",
                        OssClientUtils.BUCKET_NAME, OssClientUtils.END_POINT, avatarUrl));
            }
            throw new MyCustomException(ResultCode.AVATAR_UPLOAD_FAILURE);
        } catch (Exception e) {
            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
            return Result.failureOf(ResultCode.OSS_UPLOAD_EXCEPTION);
        }
    }
}
