package com.danbro.oss.controller;

import java.io.IOException;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import com.danbro.oss.service.OssService;
import com.danbro.oss.utils.OssClientUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname OssController
 * @Description TODO
 * @Date 2020/12/17 12:37
 * @Author Danrbo
 */
@RestController
@RequestMapping("oss")
@Slf4j

public class OssController {

    @Autowired
    OssService ossService;

    @ApiOperation("上传图片")
    @PostMapping("image/{type}")
    public Result<String> uploadAvatar(@RequestParam("file") MultipartFile image, @PathVariable String type) throws IOException {
        if (image.getSize() > OssClientUtils.UPLOAD_IMAGE_SIZE_LIMIT) {
            throw new EduException(ResultCode.OSS_UPLOAD_FILE_OVER_SIZE);
        }
        try {
            String avatarUrl = ossService.uploadAvatar(image, type);
            if (avatarUrl != null && !StringUtils.isEmpty(avatarUrl)) {
                return Result.ofSuccess(String.format("https://%s.%s/%s",
                        OssClientUtils.BUCKET_NAME, OssClientUtils.END_POINT, avatarUrl));
            }
            throw new EduException(ResultCode.OSS_UPLOAD_FILE_FAILURE);
        } catch (EduException e) {
            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
            throw new EduException(ResultCode.OSS_CLIENT_CONNECTION_ERROR);
        }
    }
}
