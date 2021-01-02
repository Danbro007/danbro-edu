package com.danbro.vod.controller;

import com.aliyuncs.exceptions.ClientException;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.vod.service.VodService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Classname VodController
 * @Description TODO 阿里云Vod服务控制器
 * @Date 2020/12/30 19:08
 * @Author Danrbo
 */
@RestController
@CrossOrigin
@RequestMapping("vod")
public class VodController {

    @Autowired
    VodService vodService;

    @ApiOperation("上传视频到阿里云")
    @PostMapping("video")
    public Result uploadVideo(@RequestParam("file") MultipartFile multipartFile) {
        try {
            String videoId = vodService.uploadVideo(multipartFile);
            return Result.successOf(ResultCode.SUCCESS, "videoId", videoId);
        } catch (IOException e) {
            throw new MyCustomException(ResultCode.VIDEO_UPLOAD_FAILURE);
        }
    }

    @ApiOperation("删除单个视频")
    @DeleteMapping("video/{id}")
    public Result deleteVideo(@PathVariable String id) {
        try {
            vodService.deleteVideo(id);
            return Result.successOf(ResultCode.SUCCESS);
        } catch (ClientException e) {
            throw new MyCustomException(ResultCode.DELETE_VIDEO_FAILURE);
        }
    }


}