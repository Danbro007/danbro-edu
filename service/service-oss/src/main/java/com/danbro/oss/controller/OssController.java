package com.danbro.oss.controller;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.oss.service.OssService;
import com.danbro.oss.utils.OssClientUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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
@CrossOrigin
public class OssController {

    @Autowired
    OssService ossService;

    @ApiOperation("上传图片")
    @PostMapping("image/{type}")
    public Result uploadAvatar(@RequestParam("file") MultipartFile image,@PathVariable String type) {
        if (image.getSize() > OssClientUtils.UPLOAD_IMAGE_SIZE_LIMIT) {
            throw new MyCustomException(ResultCode.UPLOAD_FILE_OVER_SIZE);
        }
        try {
            String avatarUrl = ossService.uploadAvatar(image,type);
            if (avatarUrl != null && !StringUtils.isEmpty(avatarUrl)) {
                return Result.successOf("imgUrl", String.format("https://%s.%s/%s",
                        OssClientUtils.BUCKET_NAME, OssClientUtils.END_POINT, avatarUrl));
            }
            throw new MyCustomException(ResultCode.AVATAR_UPLOAD_FAILURE);
        } catch (Exception e) {
            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
            return Result.failureOf(ResultCode.OSS_UPLOAD_EXCEPTION);
        }
    }
}
